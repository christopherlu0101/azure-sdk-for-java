/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.azuredatabasemigrationservice.v2017_11_15_preview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for ServiceScalability.
 */
public enum ServiceScalability {
    /** Enum value none. */
    NONE("none"),

    /** Enum value manual. */
    MANUAL("manual"),

    /** Enum value automatic. */
    AUTOMATIC("automatic");

    /** The actual serialized value for a ServiceScalability instance. */
    private String value;

    ServiceScalability(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ServiceScalability instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed ServiceScalability object, or null if unable to parse.
     */
    @JsonCreator
    public static ServiceScalability fromString(String value) {
        ServiceScalability[] items = ServiceScalability.values();
        for (ServiceScalability item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}