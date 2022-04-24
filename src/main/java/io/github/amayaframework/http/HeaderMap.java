package io.github.amayaframework.http;

import java.util.List;
import java.util.Map;

/**
 * An interface describing the header storage. Implemented in map format.
 */
public interface HeaderMap extends Map<String, List<String>> {
    /**
     * Finds the first value from the List of String values
     * for the given key (if at least one exists).
     *
     * @param key the key to search for
     * @return the first string value associated with the key
     */
    String getFirst(String key);

    /**
     * Adds the given value to the list of headers
     * for the given key. If the mapping does not
     * already exist, then it is created
     *
     * @param key   the header name
     * @param value the header value to add to the header
     */
    void add(String key, String value);

    /**
     * Sets the given value as the sole header value
     * for the given key. If the mapping does not
     * already exist, then it is created
     *
     * @param key   the header name
     * @param value the header value to set.
     */
    void set(String key, String value);
}
