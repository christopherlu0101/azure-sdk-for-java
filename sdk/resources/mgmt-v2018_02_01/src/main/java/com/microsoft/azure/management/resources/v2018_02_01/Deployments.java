/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.resources.v2018_02_01;

import com.microsoft.azure.arm.collection.SupportsCreating;
import com.microsoft.azure.arm.resources.collection.SupportsDeletingByResourceGroup;
import com.microsoft.azure.arm.resources.collection.SupportsBatchDeletion;
import com.microsoft.azure.arm.resources.collection.SupportsGettingByResourceGroup;
import rx.Observable;
import com.microsoft.azure.arm.resources.collection.SupportsListingByResourceGroup;
import rx.Completable;
import com.microsoft.azure.management.resources.v2018_02_01.implementation.DeploymentsInner;
import com.microsoft.azure.arm.model.HasInner;

/**
 * Type representing Deployments.
 */
public interface Deployments extends SupportsCreating<DeploymentExtended.DefinitionStages.Blank>, SupportsDeletingByResourceGroup, SupportsBatchDeletion, SupportsGettingByResourceGroup<DeploymentExtended>, SupportsListingByResourceGroup<DeploymentExtended>, HasInner<DeploymentsInner> {
    /**
     * Checks whether the deployment exists.
     *
     * @param resourceGroupName The name of the resource group with the deployment to check. The name is case insensitive.
     * @param deploymentName The name of the deployment to check.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable checkExistenceAsync(String resourceGroupName, String deploymentName);

    /**
     * Cancels a currently running template deployment.
     * You can cancel a deployment only if the provisioningState is Accepted or Running. After the deployment is canceled, the provisioningState is set to Canceled. Canceling a template deployment stops the currently running template deployment and leaves the resource group partially deployed.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param deploymentName The name of the deployment to cancel.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable cancelAsync(String resourceGroupName, String deploymentName);

    /**
     * Validates whether the specified template is syntactically correct and will be accepted by Azure Resource Manager..
     *
     * @param resourceGroupName The name of the resource group the template will be deployed to. The name is case insensitive.
     * @param deploymentName The name of the deployment.
     * @param properties The deployment properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<DeploymentValidateResult> validateAsync(String resourceGroupName, String deploymentName, DeploymentProperties properties);

    /**
     * Exports the template used for specified deployment.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param deploymentName The name of the deployment from which to get the template.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<DeploymentExportResult> exportTemplateAsync(String resourceGroupName, String deploymentName);

}
