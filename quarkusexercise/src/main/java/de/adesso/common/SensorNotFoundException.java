package de.adesso.common;

import java.io.Serializable;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 22.02.2022
 */
public class SensorNotFoundException extends Exception implements Serializable {

    public SensorNotFoundException(String message) {
        super(message);
    }

    public SensorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
