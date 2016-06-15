/**
 * Code generated by Microsoft (R) AutoRest Code Generator 0.17.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.keyvault.implementation.api;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyCreateParameters model.
 */
public class KeyCreateParameters {
    /**
     * The type of key to create. For valid key types, see WebKeyTypes.
     */
    @JsonProperty(required = true)
    private String kty;

    /**
     * Size of the key.
     */
    @JsonProperty(value = "key_size")
    private Integer keySize;

    /**
     * The keyOps property.
     */
    @JsonProperty(value = "key_ops")
    private List<String> keyOps;

    /**
     * The keyAttributes property.
     */
    @JsonProperty(value = "attributes")
    private KeyAttributesInner keyAttributes;

    /**
     * Application-specific metadata in the form of key-value pairs.
     */
    private Map<String, String> tags;

    /**
     * Get the kty value.
     *
     * @return the kty value
     */
    public String kty() {
        return this.kty;
    }

    /**
     * Set the kty value.
     *
     * @param kty the kty value to set
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters withKty(String kty) {
        this.kty = kty;
        return this;
    }

    /**
     * Get the keySize value.
     *
     * @return the keySize value
     */
    public Integer keySize() {
        return this.keySize;
    }

    /**
     * Set the keySize value.
     *
     * @param keySize the keySize value to set
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters withKeySize(Integer keySize) {
        this.keySize = keySize;
        return this;
    }

    /**
     * Get the keyOps value.
     *
     * @return the keyOps value
     */
    public List<String> keyOps() {
        return this.keyOps;
    }

    /**
     * Set the keyOps value.
     *
     * @param keyOps the keyOps value to set
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters withKeyOps(List<String> keyOps) {
        this.keyOps = keyOps;
        return this;
    }

    /**
     * Get the keyAttributes value.
     *
     * @return the keyAttributes value
     */
    public KeyAttributesInner keyAttributes() {
        return this.keyAttributes;
    }

    /**
     * Set the keyAttributes value.
     *
     * @param keyAttributes the keyAttributes value to set
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters withKeyAttributes(KeyAttributesInner keyAttributes) {
        this.keyAttributes = keyAttributes;
        return this;
    }

    /**
     * Get the tags value.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set the tags value.
     *
     * @param tags the tags value to set
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

}
