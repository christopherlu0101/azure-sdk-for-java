/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.managedapplications.v2019_07_01;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for ApplicationDefinitionArtifactName.
 */
public final class ApplicationDefinitionArtifactName extends ExpandableStringEnum<ApplicationDefinitionArtifactName> {
    /** Static value NotSpecified for ApplicationDefinitionArtifactName. */
    public static final ApplicationDefinitionArtifactName NOT_SPECIFIED = fromString("NotSpecified");

    /** Static value ApplicationResourceTemplate for ApplicationDefinitionArtifactName. */
    public static final ApplicationDefinitionArtifactName APPLICATION_RESOURCE_TEMPLATE = fromString("ApplicationResourceTemplate");

    /** Static value CreateUiDefinition for ApplicationDefinitionArtifactName. */
    public static final ApplicationDefinitionArtifactName CREATE_UI_DEFINITION = fromString("CreateUiDefinition");

    /** Static value MainTemplateParameters for ApplicationDefinitionArtifactName. */
    public static final ApplicationDefinitionArtifactName MAIN_TEMPLATE_PARAMETERS = fromString("MainTemplateParameters");

    /**
     * Creates or finds a ApplicationDefinitionArtifactName from its string representation.
     * @param name a name to look for
     * @return the corresponding ApplicationDefinitionArtifactName
     */
    @JsonCreator
    public static ApplicationDefinitionArtifactName fromString(String name) {
        return fromString(name, ApplicationDefinitionArtifactName.class);
    }

    /**
     * @return known ApplicationDefinitionArtifactName values
     */
    public static Collection<ApplicationDefinitionArtifactName> values() {
        return values(ApplicationDefinitionArtifactName.class);
    }
}
