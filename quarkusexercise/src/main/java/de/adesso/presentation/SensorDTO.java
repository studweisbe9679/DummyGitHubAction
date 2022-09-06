package de.adesso.presentation;

import lombok.Data;

import javax.ws.rs.core.Response;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@Data
public class SensorDTO {

    private String name;

    private String location;

}
