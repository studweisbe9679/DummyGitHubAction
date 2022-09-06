package de.adesso.presentation;

import de.adesso.domain.Sensor;
import org.mapstruct.Mapper;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@Mapper(componentModel = "cdi")
public interface ISensorMapper {

    Sensor mapSensorDtoToSensor(SensorDTO dto);
}
