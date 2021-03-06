/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.mixedreality.v2019_02_28_preview.implementation;

import com.google.common.reflect.TypeToken;
import com.microsoft.azure.AzureClient;
import com.microsoft.azure.AzureServiceClient;
import com.microsoft.azure.management.mixedreality.v2019_02_28_preview.CheckNameAvailabilityRequest;
import com.microsoft.azure.management.mixedreality.v2019_02_28_preview.ErrorResponseException;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.Validator;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * Initializes a new instance of the MixedRealityClientImpl class.
 */
public class MixedRealityClientImpl extends AzureServiceClient {
    /** The Retrofit service to perform REST calls. */
    private MixedRealityClientService service;
    /** the {@link AzureClient} used for long running operations. */
    private AzureClient azureClient;

    /**
     * Gets the {@link AzureClient} used for long running operations.
     * @return the azure client;
     */
    public AzureClient getAzureClient() {
        return this.azureClient;
    }

    /** Version of the API to be used with the client request. */
    private String apiVersion;

    /**
     * Gets Version of the API to be used with the client request.
     *
     * @return the apiVersion value.
     */
    public String apiVersion() {
        return this.apiVersion;
    }

    /** Azure subscription ID. */
    private String subscriptionId;

    /**
     * Gets Azure subscription ID.
     *
     * @return the subscriptionId value.
     */
    public String subscriptionId() {
        return this.subscriptionId;
    }

    /**
     * Sets Azure subscription ID.
     *
     * @param subscriptionId the subscriptionId value.
     * @return the service client itself
     */
    public MixedRealityClientImpl withSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    /** The preferred language for the response. */
    private String acceptLanguage;

    /**
     * Gets The preferred language for the response.
     *
     * @return the acceptLanguage value.
     */
    public String acceptLanguage() {
        return this.acceptLanguage;
    }

    /**
     * Sets The preferred language for the response.
     *
     * @param acceptLanguage the acceptLanguage value.
     * @return the service client itself
     */
    public MixedRealityClientImpl withAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    /** The retry timeout in seconds for Long Running Operations. Default value is 30. */
    private int longRunningOperationRetryTimeout;

    /**
     * Gets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @return the longRunningOperationRetryTimeout value.
     */
    public int longRunningOperationRetryTimeout() {
        return this.longRunningOperationRetryTimeout;
    }

    /**
     * Sets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @param longRunningOperationRetryTimeout the longRunningOperationRetryTimeout value.
     * @return the service client itself
     */
    public MixedRealityClientImpl withLongRunningOperationRetryTimeout(int longRunningOperationRetryTimeout) {
        this.longRunningOperationRetryTimeout = longRunningOperationRetryTimeout;
        return this;
    }

    /** Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true. */
    private boolean generateClientRequestId;

    /**
     * Gets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @return the generateClientRequestId value.
     */
    public boolean generateClientRequestId() {
        return this.generateClientRequestId;
    }

    /**
     * Sets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @param generateClientRequestId the generateClientRequestId value.
     * @return the service client itself
     */
    public MixedRealityClientImpl withGenerateClientRequestId(boolean generateClientRequestId) {
        this.generateClientRequestId = generateClientRequestId;
        return this;
    }

    /**
     * The OperationsInner object to access its operations.
     */
    private OperationsInner operations;

    /**
     * Gets the OperationsInner object to access its operations.
     * @return the OperationsInner object.
     */
    public OperationsInner operations() {
        return this.operations;
    }

    /**
     * The SpatialAnchorsAccountsInner object to access its operations.
     */
    private SpatialAnchorsAccountsInner spatialAnchorsAccounts;

    /**
     * Gets the SpatialAnchorsAccountsInner object to access its operations.
     * @return the SpatialAnchorsAccountsInner object.
     */
    public SpatialAnchorsAccountsInner spatialAnchorsAccounts() {
        return this.spatialAnchorsAccounts;
    }

    /**
     * Initializes an instance of MixedRealityClient client.
     *
     * @param credentials the management credentials for Azure
     */
    public MixedRealityClientImpl(ServiceClientCredentials credentials) {
        this("https://management.azure.com", credentials);
    }

    /**
     * Initializes an instance of MixedRealityClient client.
     *
     * @param baseUrl the base URL of the host
     * @param credentials the management credentials for Azure
     */
    public MixedRealityClientImpl(String baseUrl, ServiceClientCredentials credentials) {
        super(baseUrl, credentials);
        initialize();
    }

    /**
     * Initializes an instance of MixedRealityClient client.
     *
     * @param restClient the REST client to connect to Azure.
     */
    public MixedRealityClientImpl(RestClient restClient) {
        super(restClient);
        initialize();
    }

    protected void initialize() {
        this.apiVersion = "2019-02-28-preview";
        this.acceptLanguage = "en-US";
        this.longRunningOperationRetryTimeout = 30;
        this.generateClientRequestId = true;
        this.operations = new OperationsInner(restClient().retrofit(), this);
        this.spatialAnchorsAccounts = new SpatialAnchorsAccountsInner(restClient().retrofit(), this);
        this.azureClient = new AzureClient(this);
        initializeService();
    }

    /**
     * Gets the User-Agent header for the client.
     *
     * @return the user agent string.
     */
    @Override
    public String userAgent() {
        return String.format("%s (%s, %s, auto-generated)", super.userAgent(), "MixedRealityClient", "2019-02-28-preview");
    }

    private void initializeService() {
        service = restClient().retrofit().create(MixedRealityClientService.class);
    }

    /**
     * The interface defining all the services for MixedRealityClient to be
     * used by Retrofit to perform actually REST calls.
     */
    interface MixedRealityClientService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.mixedreality.v2019_02_28_preview.MixedRealityClient checkNameAvailabilityLocal" })
        @POST("subscriptions/{subscriptionId}/providers/Microsoft.MixedReality/locations/{location}/checkNameAvailability")
        Observable<Response<ResponseBody>> checkNameAvailabilityLocal(@Path("subscriptionId") String subscriptionId, @Path("location") String location, @Body CheckNameAvailabilityRequest checkNameAvailability, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * Check Name Availability for global uniqueness.
     *
     * @param location The location in which uniqueness will be verified.
     * @param checkNameAvailability Check Name Availability Request.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the CheckNameAvailabilityResponseInner object if successful.
     */
    public CheckNameAvailabilityResponseInner checkNameAvailabilityLocal(String location, CheckNameAvailabilityRequest checkNameAvailability) {
        return checkNameAvailabilityLocalWithServiceResponseAsync(location, checkNameAvailability).toBlocking().single().body();
    }

    /**
     * Check Name Availability for global uniqueness.
     *
     * @param location The location in which uniqueness will be verified.
     * @param checkNameAvailability Check Name Availability Request.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<CheckNameAvailabilityResponseInner> checkNameAvailabilityLocalAsync(String location, CheckNameAvailabilityRequest checkNameAvailability, final ServiceCallback<CheckNameAvailabilityResponseInner> serviceCallback) {
        return ServiceFuture.fromResponse(checkNameAvailabilityLocalWithServiceResponseAsync(location, checkNameAvailability), serviceCallback);
    }

    /**
     * Check Name Availability for global uniqueness.
     *
     * @param location The location in which uniqueness will be verified.
     * @param checkNameAvailability Check Name Availability Request.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CheckNameAvailabilityResponseInner object
     */
    public Observable<CheckNameAvailabilityResponseInner> checkNameAvailabilityLocalAsync(String location, CheckNameAvailabilityRequest checkNameAvailability) {
        return checkNameAvailabilityLocalWithServiceResponseAsync(location, checkNameAvailability).map(new Func1<ServiceResponse<CheckNameAvailabilityResponseInner>, CheckNameAvailabilityResponseInner>() {
            @Override
            public CheckNameAvailabilityResponseInner call(ServiceResponse<CheckNameAvailabilityResponseInner> response) {
                return response.body();
            }
        });
    }

    /**
     * Check Name Availability for global uniqueness.
     *
     * @param location The location in which uniqueness will be verified.
     * @param checkNameAvailability Check Name Availability Request.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CheckNameAvailabilityResponseInner object
     */
    public Observable<ServiceResponse<CheckNameAvailabilityResponseInner>> checkNameAvailabilityLocalWithServiceResponseAsync(String location, CheckNameAvailabilityRequest checkNameAvailability) {
        if (this.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.subscriptionId() is required and cannot be null.");
        }
        if (location == null) {
            throw new IllegalArgumentException("Parameter location is required and cannot be null.");
        }
        if (checkNameAvailability == null) {
            throw new IllegalArgumentException("Parameter checkNameAvailability is required and cannot be null.");
        }
        if (this.apiVersion() == null) {
            throw new IllegalArgumentException("Parameter this.apiVersion() is required and cannot be null.");
        }
        Validator.validate(checkNameAvailability);
        return service.checkNameAvailabilityLocal(this.subscriptionId(), location, checkNameAvailability, this.apiVersion(), this.acceptLanguage(), this.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<CheckNameAvailabilityResponseInner>>>() {
                @Override
                public Observable<ServiceResponse<CheckNameAvailabilityResponseInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<CheckNameAvailabilityResponseInner> clientResponse = checkNameAvailabilityLocalDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<CheckNameAvailabilityResponseInner> checkNameAvailabilityLocalDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<CheckNameAvailabilityResponseInner, ErrorResponseException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<CheckNameAvailabilityResponseInner>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

}
