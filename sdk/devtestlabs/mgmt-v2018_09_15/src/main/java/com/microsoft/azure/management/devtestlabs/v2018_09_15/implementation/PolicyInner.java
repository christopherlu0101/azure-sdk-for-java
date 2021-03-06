/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.devtestlabs.v2018_09_15.implementation;

import com.microsoft.azure.management.devtestlabs.v2018_09_15.PolicyStatus;
import com.microsoft.azure.management.devtestlabs.v2018_09_15.PolicyFactName;
import com.microsoft.azure.management.devtestlabs.v2018_09_15.PolicyEvaluatorType;
import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.rest.SkipParentValidation;
import com.microsoft.azure.Resource;

/**
 * A Policy.
 */
@JsonFlatten
@SkipParentValidation
public class PolicyInner extends Resource {
    /**
     * The description of the policy.
     */
    @JsonProperty(value = "properties.description")
    private String description;

    /**
     * The status of the policy. Possible values include: 'Enabled',
     * 'Disabled'.
     */
    @JsonProperty(value = "properties.status")
    private PolicyStatus status;

    /**
     * The fact name of the policy (e.g. LabVmCount, LabVmSize,
     * MaxVmsAllowedPerLab, etc. Possible values include:
     * 'UserOwnedLabVmCount', 'UserOwnedLabPremiumVmCount', 'LabVmCount',
     * 'LabPremiumVmCount', 'LabVmSize', 'GalleryImage',
     * 'UserOwnedLabVmCountInSubnet', 'LabTargetCost', 'EnvironmentTemplate',
     * 'ScheduleEditPermission'.
     */
    @JsonProperty(value = "properties.factName")
    private PolicyFactName factName;

    /**
     * The fact data of the policy.
     */
    @JsonProperty(value = "properties.factData")
    private String factData;

    /**
     * The threshold of the policy (i.e. a number for MaxValuePolicy, and a
     * JSON array of values for AllowedValuesPolicy).
     */
    @JsonProperty(value = "properties.threshold")
    private String threshold;

    /**
     * The evaluator type of the policy (i.e. AllowedValuesPolicy,
     * MaxValuePolicy). Possible values include: 'AllowedValuesPolicy',
     * 'MaxValuePolicy'.
     */
    @JsonProperty(value = "properties.evaluatorType")
    private PolicyEvaluatorType evaluatorType;

    /**
     * The creation date of the policy.
     */
    @JsonProperty(value = "properties.createdDate", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime createdDate;

    /**
     * The provisioning status of the resource.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * The unique immutable identifier of a resource (Guid).
     */
    @JsonProperty(value = "properties.uniqueIdentifier", access = JsonProperty.Access.WRITE_ONLY)
    private String uniqueIdentifier;

    /**
     * Get the description of the policy.
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set the description of the policy.
     *
     * @param description the description value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the status of the policy. Possible values include: 'Enabled', 'Disabled'.
     *
     * @return the status value
     */
    public PolicyStatus status() {
        return this.status;
    }

    /**
     * Set the status of the policy. Possible values include: 'Enabled', 'Disabled'.
     *
     * @param status the status value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withStatus(PolicyStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the fact name of the policy (e.g. LabVmCount, LabVmSize, MaxVmsAllowedPerLab, etc. Possible values include: 'UserOwnedLabVmCount', 'UserOwnedLabPremiumVmCount', 'LabVmCount', 'LabPremiumVmCount', 'LabVmSize', 'GalleryImage', 'UserOwnedLabVmCountInSubnet', 'LabTargetCost', 'EnvironmentTemplate', 'ScheduleEditPermission'.
     *
     * @return the factName value
     */
    public PolicyFactName factName() {
        return this.factName;
    }

    /**
     * Set the fact name of the policy (e.g. LabVmCount, LabVmSize, MaxVmsAllowedPerLab, etc. Possible values include: 'UserOwnedLabVmCount', 'UserOwnedLabPremiumVmCount', 'LabVmCount', 'LabPremiumVmCount', 'LabVmSize', 'GalleryImage', 'UserOwnedLabVmCountInSubnet', 'LabTargetCost', 'EnvironmentTemplate', 'ScheduleEditPermission'.
     *
     * @param factName the factName value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withFactName(PolicyFactName factName) {
        this.factName = factName;
        return this;
    }

    /**
     * Get the fact data of the policy.
     *
     * @return the factData value
     */
    public String factData() {
        return this.factData;
    }

    /**
     * Set the fact data of the policy.
     *
     * @param factData the factData value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withFactData(String factData) {
        this.factData = factData;
        return this;
    }

    /**
     * Get the threshold of the policy (i.e. a number for MaxValuePolicy, and a JSON array of values for AllowedValuesPolicy).
     *
     * @return the threshold value
     */
    public String threshold() {
        return this.threshold;
    }

    /**
     * Set the threshold of the policy (i.e. a number for MaxValuePolicy, and a JSON array of values for AllowedValuesPolicy).
     *
     * @param threshold the threshold value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withThreshold(String threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * Get the evaluator type of the policy (i.e. AllowedValuesPolicy, MaxValuePolicy). Possible values include: 'AllowedValuesPolicy', 'MaxValuePolicy'.
     *
     * @return the evaluatorType value
     */
    public PolicyEvaluatorType evaluatorType() {
        return this.evaluatorType;
    }

    /**
     * Set the evaluator type of the policy (i.e. AllowedValuesPolicy, MaxValuePolicy). Possible values include: 'AllowedValuesPolicy', 'MaxValuePolicy'.
     *
     * @param evaluatorType the evaluatorType value to set
     * @return the PolicyInner object itself.
     */
    public PolicyInner withEvaluatorType(PolicyEvaluatorType evaluatorType) {
        this.evaluatorType = evaluatorType;
        return this;
    }

    /**
     * Get the creation date of the policy.
     *
     * @return the createdDate value
     */
    public DateTime createdDate() {
        return this.createdDate;
    }

    /**
     * Get the provisioning status of the resource.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the unique immutable identifier of a resource (Guid).
     *
     * @return the uniqueIdentifier value
     */
    public String uniqueIdentifier() {
        return this.uniqueIdentifier;
    }

}
