package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all Physical Information Objects.
 *
 * @author Kyle Michel
 */
public class Pio {

    /**
     * Add an unsupported field to this object.
     *
     * @param key String with the key of the field.
     * @param value Object with the value of the field.
     */
    @JsonAnySetter
    public void putUnsupportedField(final String key, final Object value) {
        if (this.unsupportedFields == null) {
            this.unsupportedFields = new HashMap<>();
        }
        this.unsupportedFields.put(key, value);
    }

    /**
     * Add an unsupported field to this object.
     *
     * @param key String with the key of the field.
     * @param value Object with the value of the field.
     * @return This object.
     */
    public Pio withUnsupportedField(final String key, final Object value) {
        this.putUnsupportedField(key, value);
        return this;
    }

    /**
     * Get the map of all unsupported fields.
     *
     * @return Map of strings to objects with the unsupported fields.
     */
    @JsonAnyGetter
    private Map<String, Object> getUnsupportedFields() { // Private since only Jackson should use it
        return this.unsupportedFields;
    }

    /**
     * Get the number of unsupported fields.
     *
     * @return Number of unsupported fields.
     */
    @JsonIgnore
    public int getNumUnsupportedFields() {
        return (this.unsupportedFields == null) ? 0 : this.unsupportedFields.size();
    }

    /**
     * Return whether there is an unsupported field with the input key.
     *
     * @param key Key to check whether in unsupported fields.
     * @return True if the key exists in the unsupported fields.
     */
    public boolean hasUnsupportedFieldKey(final String key) {
        return (this.unsupportedFields != null) && this.unsupportedFields.containsKey(key);
    }

    /**
     * Get an {@link Iterable} object to iterate over key/value pairs of the unsupported fields.
     *
     * @return {@link Iterable} object for the unsupported fields.
     */
    public Iterable<Map.Entry<String, Object>> unsupportedFields() {
        return (this.unsupportedFields == null) ? Collections.emptySet() : this.unsupportedFields.entrySet();
    }

    /**
     * Get the value of an unsupported field.
     *
     * @param key String with the key of the field.
     * @return Object with the value of the field or a null pointer if the field does not exist.
     */
    @JsonIgnore
    public Object getUnsupportedFieldValue(final String key) {
        return (this.unsupportedFields == null) ? null : this.unsupportedFields.get(key);
    }

    /** Map of unsupported field names to their values. */
    private Map<String, Object> unsupportedFields;
}