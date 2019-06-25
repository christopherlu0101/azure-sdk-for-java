// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.eventhubs;

import com.azure.core.amqp.AmqpConnection;
import com.azure.core.amqp.exception.AmqpException;
import com.azure.core.amqp.exception.ErrorContext;
import com.azure.core.implementation.util.ImplUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.messaging.eventhubs.implementation.AmqpReceiveLink;
import com.azure.messaging.eventhubs.implementation.AmqpResponseMapper;
import com.azure.messaging.eventhubs.implementation.AmqpSendLink;
import com.azure.messaging.eventhubs.implementation.ConnectionOptions;
import com.azure.messaging.eventhubs.implementation.EventHubConnection;
import com.azure.messaging.eventhubs.implementation.EventHubManagementNode;
import com.azure.messaging.eventhubs.implementation.EventHubSession;
import com.azure.messaging.eventhubs.implementation.ManagementChannel;
import com.azure.messaging.eventhubs.implementation.ReactorConnection;
import com.azure.messaging.eventhubs.implementation.ReactorHandlerProvider;
import com.azure.messaging.eventhubs.implementation.ReactorProvider;
import com.azure.messaging.eventhubs.implementation.StringUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The main point of interaction with Azure Event Hubs, the client offers a connection to a specific Event Hub within
 * the Event Hubs namespace and offers operations for sending event data, receiving events, and inspecting the connected
 * Event Hub.
 */
public class EventHubClient implements Closeable {
    /**
     * The name of the default consumer group in the Event Hubs service.
     */
    public static final String DEFAULT_CONSUMER_GROUP_NAME = "$Default";

    private static final String RECEIVER_ENTITY_PATH_FORMAT = "%s/ConsumerGroups/%s/Partitions/%s";
    private static final String SENDER_ENTITY_PATH_FORMAT = "%s/Partitions/%s";

    private final ClientLogger logger = new ClientLogger(EventHubClient.class);
    private final String connectionId;
    private final Mono<EventHubConnection> connectionMono;
    private final AtomicBoolean hasConnection = new AtomicBoolean(false);
    private final ConnectionOptions connectionOptions;
    private final String eventHubPath;
    private final EventHubProducerOptions defaultProducerOptions;
    private final EventHubConsumerOptions defaultConsumerOptions;

    EventHubClient(ConnectionOptions connectionOptions, ReactorProvider provider, ReactorHandlerProvider handlerProvider) {
        Objects.requireNonNull(connectionOptions);
        Objects.requireNonNull(provider);
        Objects.requireNonNull(handlerProvider);

        this.connectionOptions = connectionOptions;
        this.eventHubPath = connectionOptions.eventHubPath();
        this.connectionId = StringUtil.getRandomString("MF");
        this.connectionMono = Mono.fromCallable(() -> {
            return (EventHubConnection) new ReactorConnection(connectionId, connectionOptions, provider, handlerProvider, new ResponseMapper());
        }).doOnSubscribe(c -> hasConnection.set(true))
            .cache();

        this.defaultProducerOptions = new EventHubProducerOptions()
            .retry(connectionOptions.retryPolicy())
            .timeout(connectionOptions.timeout());
        this.defaultConsumerOptions = new EventHubConsumerOptions()
            .retry(connectionOptions.retryPolicy())
            .scheduler(connectionOptions.scheduler());
    }

    /**
     * Retrieves information about an Event Hub, including the number of partitions present and their identifiers.
     *
     * @return The set of information for the Event Hub that this client is associated with.
     */
    public Mono<EventHubProperties> getProperties() {
        return connectionMono.flatMap(connection -> connection.getManagementNode().flatMap(EventHubManagementNode::getEventHubProperties));
    }

    /**
     * Retrieves the identifiers for the partitions of an Event Hub.
     *
     * @return A Flux of identifiers for the partitions of an Event Hub.
     */
    public Flux<String> getPartitionIds() {
        return getProperties().flatMapMany(properties -> Flux.fromArray(properties.partitionIds()));
    }

    /**
     * Retrieves information about a specific partition for an Event Hub, including elements that describe the available
     * events in the partition event stream.
     *
     * @param partitionId The unique identifier of a partition associated with the Event Hub.
     * @return The set of information for the requested partition under the Event Hub this client is associated with.
     */
    public Mono<PartitionProperties> getPartitionProperties(String partitionId) {
        return connectionMono.flatMap(
            connection -> connection.getManagementNode().flatMap(node -> {
                return node.getPartitionProperties(partitionId);
            }));
    }

    /**
     * Creates an Event Hub producer responsible for transmitting {@link EventData} to the Event Hub, grouped together
     * in batches. Event data is automatically routed to an available partition.
     *
     * @return A new {@link EventHubProducer}.
     */
    public EventHubProducer createProducer() {
        return createProducer(defaultProducerOptions);
    }

    /**
     * Creates an Event Hub producer responsible for transmitting {@link EventData} to the Event Hub, grouped together
     * in batches. If {@link EventHubProducerOptions#partitionId() options.partitionId()} is not {@code null}, the
     * events are routed to that specific partition. Otherwise, events are automatically routed to an available
     * partition.
     *
     * @param options The set of options to apply when creating the producer.
     * @return A new {@link EventHubProducer}.
     * @throws NullPointerException if {@code options} is {@code null}.
     */
    public EventHubProducer createProducer(EventHubProducerOptions options) {
        Objects.requireNonNull(options);

        final EventHubProducerOptions clonedOptions = options.clone();
        if (clonedOptions.timeout() == null) {
            clonedOptions.timeout(connectionOptions.timeout());
        }
        if (clonedOptions.retry() == null) {
            clonedOptions.retry(connectionOptions.retryPolicy());
        }

        final String entityPath;
        final String linkName;

        if (ImplUtils.isNullOrEmpty(options.partitionId())) {
            entityPath = eventHubPath;
            linkName = StringUtil.getRandomString("EC");
        } else {
            entityPath = String.format(Locale.US, SENDER_ENTITY_PATH_FORMAT, eventHubPath, options.partitionId());
            linkName = StringUtil.getRandomString("PS");
        }

        final Mono<AmqpSendLink> amqpLinkMono = connectionMono.flatMap(connection -> connection.createSession(entityPath))
            .flatMap(session -> {
                logger.asInfo().log("Creating producer.");
                return session.createProducer(linkName, entityPath, clonedOptions.timeout(), clonedOptions.retry())
                    .cast(AmqpSendLink.class);
            });

        return new EventHubProducer(amqpLinkMono, clonedOptions);
    }

    /**
     * Creates an Event Hub consumer responsible for reading {@link EventData} from a specific Event Hub partition, as a
     * member of the specified consumer group, and begins reading events from the {@code eventPosition}.
     *
     * The consumer created is non-exclusive, allowing multiple consumers from the same consumer group to be actively
     * reading events from the partition. These non-exclusive consumers are sometimes referred to as "Non-epoch
     * Consumers".
     *
     * @param consumerGroup The name of the consumer group this consumer is associated with. Events are read in
     *         the context of this group. The name of the consumer group that is created by default is {@link
     *         #DEFAULT_CONSUMER_GROUP_NAME "$Default"}.
     * @param partitionId The identifier of the Event Hub partition.
     * @param eventPosition The position within the partition where the consumer should begin reading events.
     * @return A new {@link EventHubConsumer} that receives events from the partition at the given position.
     * @throws NullPointerException If {@code eventPosition}, or {@code options} is {@code null}.
     * @throws IllegalArgumentException If {@code consumerGroup} or {@code partitionId} is {@code null} or an
     *         empty string.
     */
    public EventHubConsumer createConsumer(String consumerGroup, String partitionId, EventPosition eventPosition) {
        return createConsumer(consumerGroup, partitionId, eventPosition, defaultConsumerOptions);
    }

    /**
     * Creates an Event Hub consumer responsible for reading {@link EventData} from a specific Event Hub partition, as a
     * member of the configured consumer group, and begins reading events from the specified {@code eventPosition}.
     *
     * <p>
     * A consumer may be exclusive, which asserts ownership over the partition for the consumer group to ensure that
     * only one consumer from that group is reading the from the partition. These exclusive consumers are sometimes
     * referred to as "Epoch Consumers."
     *
     * A consumer may also be non-exclusive, allowing multiple consumers from the same consumer group to be actively
     * reading events from the partition. These non-exclusive consumers are sometimes referred to as "Non-epoch
     * Consumers."
     *
     * Designating a consumer as exclusive may be specified in the {@code options}, by setting {@link
     * EventHubConsumerOptions#ownerLevel(Long)} to a non-null value. By default, consumers are created as
     * non-exclusive.
     * </p>
     *
     * @param consumerGroup The name of the consumer group this consumer is associated with. Events are read in
     *         the context of this group. The name of the consumer group that is created by default is {@link
     *         #DEFAULT_CONSUMER_GROUP_NAME "$Default"}.
     * @param partitionId The identifier of the Event Hub partition from which events will be received.
     * @param eventPosition The position within the partition where the consumer should begin reading events.
     * @param options The set of options to apply when creating the consumer.
     * @return An new {@link EventHubConsumer} that receives events from the partition with all configured {@link
     *         EventHubConsumerOptions}.
     * @throws NullPointerException If {@code eventPosition}, or {@code options} is {@code null}.
     * @throws IllegalArgumentException If {@code consumerGroup} or {@code partitionId} is {@code null} or an
     *         empty string.
     */
    public EventHubConsumer createConsumer(String consumerGroup, String partitionId, EventPosition eventPosition,
                                           EventHubConsumerOptions options) {
        Objects.requireNonNull(eventPosition);
        Objects.requireNonNull(options);

        if (ImplUtils.isNullOrEmpty(consumerGroup)) {
            throw new IllegalArgumentException("'consumerGroup' cannot be null or empty.");
        }
        if (ImplUtils.isNullOrEmpty(partitionId)) {
            throw new IllegalArgumentException("'partitionId' cannot be null or empty.");
        }

        final EventHubConsumerOptions clonedOptions = options.clone();
        if (clonedOptions.scheduler() == null) {
            clonedOptions.scheduler(connectionOptions.scheduler());
        }
        if (clonedOptions.retry() == null) {
            clonedOptions.retry(connectionOptions.retryPolicy());
        }

        final String linkName = StringUtil.getRandomString("PR");
        final String entityPath = String.format(Locale.US, RECEIVER_ENTITY_PATH_FORMAT, eventHubPath, consumerGroup, partitionId);

        final Mono<AmqpReceiveLink> receiveLinkMono = connectionMono.flatMap(connection -> {
            return connection.createSession(entityPath).cast(EventHubSession.class);
        }).flatMap(session -> {
            logger.asInfo().log("Creating consumer.");
            return session.createConsumer(linkName, entityPath, eventPosition.getExpression(), connectionOptions.timeout(),
                clonedOptions.retry(), options.ownerLevel(), options.identifier()).cast(AmqpReceiveLink.class);
        });

        return new EventHubConsumer(receiveLinkMono, clonedOptions, connectionOptions.timeout());
    }

    /**
     * Closes and disposes of connection to service. Any {@link EventHubConsumer EventHubConsumers} and {@link
     * EventHubProducer EventHubProducers} created with this instance will have their connections closed.
     */
    @Override
    public void close() {
        if (hasConnection.getAndSet(false)) {
            try {
                final AmqpConnection connection = connectionMono.block(connectionOptions.timeout());
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException exception) {
                throw new AmqpException(false, "Unable to close connection to service", exception,
                    new ErrorContext(connectionOptions.host()));
            }
        }
    }

    private static class ResponseMapper implements AmqpResponseMapper {
        @Override
        public EventHubProperties toEventHubProperties(Map<?, ?> amqpBody) {
            return new EventHubProperties(
                (String) amqpBody.get(ManagementChannel.MANAGEMENT_ENTITY_NAME_KEY),
                ((Date) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_CREATED_AT)).toInstant(),
                (String[]) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_PARTITION_IDS));
        }

        @Override
        public PartitionProperties toPartitionProperties(Map<?, ?> amqpBody) {
            return new PartitionProperties(
                (String) amqpBody.get(ManagementChannel.MANAGEMENT_ENTITY_NAME_KEY),
                (String) amqpBody.get(ManagementChannel.MANAGEMENT_PARTITION_NAME_KEY),
                (Long) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_BEGIN_SEQUENCE_NUMBER),
                (Long) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_LAST_ENQUEUED_SEQUENCE_NUMBER),
                (String) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_LAST_ENQUEUED_OFFSET),
                ((Date) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_LAST_ENQUEUED_TIME_UTC)).toInstant(),
                (Boolean) amqpBody.get(ManagementChannel.MANAGEMENT_RESULT_PARTITION_IS_EMPTY));
        }
    }
}