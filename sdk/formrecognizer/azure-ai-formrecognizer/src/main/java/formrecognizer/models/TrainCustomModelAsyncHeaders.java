// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package formrecognizer.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines headers for TrainCustomModelAsync operation.
 */
@Fluent
public final class TrainCustomModelAsyncHeaders {
    /*
     * Location and ID of the model being trained. The status of model training
     * is specified in the status property at the model location.
     */
    @JsonProperty(value = "Location")
    private String location;

    /**
     * Get the location property: Location and ID of the model being trained.
     * The status of model training is specified in the status property at the
     * model location.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: Location and ID of the model being trained.
     * The status of model training is specified in the status property at the
     * model location.
     *
     * @param location the location value to set.
     * @return the TrainCustomModelAsyncHeaders object itself.
     */
    public TrainCustomModelAsyncHeaders setLocation(String location) {
        this.location = location;
        return this;
    }
}
