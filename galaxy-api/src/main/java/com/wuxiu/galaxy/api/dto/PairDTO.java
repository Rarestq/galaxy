
package com.wuxiu.galaxy.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>A convenience class to represent name-value pairs.</p>
 *
 * @since JavaFX 2.0
 */
@Data
public class PairDTO<K, V> implements Serializable {

    /**
     * Key of this
     */
    private K key;

    /**
     * Value of this
     */
    private V value;
}
