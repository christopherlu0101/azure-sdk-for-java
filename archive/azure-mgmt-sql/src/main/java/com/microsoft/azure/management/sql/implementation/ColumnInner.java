/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.Resource;

/**
 * Represents an Azure SQL Database table column.
 */
@JsonFlatten
public class ColumnInner extends Resource {
    /**
     * The type of Azure SQL Database table column.
     */
    @JsonProperty(value = "properties.columnType", access = JsonProperty.Access.WRITE_ONLY)
    private String columnType;

    /**
     * Get the columnType value.
     *
     * @return the columnType value
     */
    public String columnType() {
        return this.columnType;
    }

}