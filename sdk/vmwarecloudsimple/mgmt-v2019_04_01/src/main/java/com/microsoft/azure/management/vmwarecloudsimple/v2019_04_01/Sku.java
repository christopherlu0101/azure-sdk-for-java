/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.vmwarecloudsimple.v2019_04_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purchase SKU for CloudSimple paid resources.
 */
public class Sku {
    /**
     * The capacity of the SKU.
     */
    @JsonProperty(value = "capacity")
    private String capacity;

    /**
     * dedicatedCloudNode example: 8 x Ten-Core Intel® Xeon® Processor E5-2640
     * v4 2.40GHz 25MB Cache (90W); 12 x 64GB PC4-19200 2400MHz DDR4 ECC
     * Registered DIMM, ...
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * If the service has different generations of hardware, for the same SKU,
     * then that can be captured here.
     */
    @JsonProperty(value = "family")
    private String family;

    /**
     * The name of the SKU for VMWare CloudSimple Node.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /**
     * The tier of the SKU.
     */
    @JsonProperty(value = "tier")
    private String tier;

    /**
     * Get the capacity of the SKU.
     *
     * @return the capacity value
     */
    public String capacity() {
        return this.capacity;
    }

    /**
     * Set the capacity of the SKU.
     *
     * @param capacity the capacity value to set
     * @return the Sku object itself.
     */
    public Sku withCapacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Get dedicatedCloudNode example: 8 x Ten-Core Intel® Xeon® Processor E5-2640 v4 2.40GHz 25MB Cache (90W); 12 x 64GB PC4-19200 2400MHz DDR4 ECC Registered DIMM, ...
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set dedicatedCloudNode example: 8 x Ten-Core Intel® Xeon® Processor E5-2640 v4 2.40GHz 25MB Cache (90W); 12 x 64GB PC4-19200 2400MHz DDR4 ECC Registered DIMM, ...
     *
     * @param description the description value to set
     * @return the Sku object itself.
     */
    public Sku withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get if the service has different generations of hardware, for the same SKU, then that can be captured here.
     *
     * @return the family value
     */
    public String family() {
        return this.family;
    }

    /**
     * Set if the service has different generations of hardware, for the same SKU, then that can be captured here.
     *
     * @param family the family value to set
     * @return the Sku object itself.
     */
    public Sku withFamily(String family) {
        this.family = family;
        return this;
    }

    /**
     * Get the name of the SKU for VMWare CloudSimple Node.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name of the SKU for VMWare CloudSimple Node.
     *
     * @param name the name value to set
     * @return the Sku object itself.
     */
    public Sku withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the tier of the SKU.
     *
     * @return the tier value
     */
    public String tier() {
        return this.tier;
    }

    /**
     * Set the tier of the SKU.
     *
     * @param tier the tier value to set
     * @return the Sku object itself.
     */
    public Sku withTier(String tier) {
        this.tier = tier;
        return this;
    }

}
