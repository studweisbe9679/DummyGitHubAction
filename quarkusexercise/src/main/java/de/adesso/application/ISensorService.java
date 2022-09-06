package de.adesso.application;

import de.adesso.common.SensorNotFoundException;
import de.adesso.domain.Sensor;

import java.util.List;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
public interface ISensorService {

    Sensor getSensor(final long id) throws SensorNotFoundException;

    List<Sensor> getAllSensors();

    Sensor addSensor(final Sensor sensor);

}
