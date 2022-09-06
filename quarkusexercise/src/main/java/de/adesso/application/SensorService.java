package de.adesso.application;

import de.adesso.common.SensorNotFoundException;
import de.adesso.domain.Sensor;
import de.adesso.persistence.SensorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@ApplicationScoped
public class SensorService implements ISensorService {

    private final SensorRepository sensorRepository;

    @Inject
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Sensor getSensor(long id) throws SensorNotFoundException {
        return sensorRepository.findByIdOptional(id)
                .orElseThrow(() -> new SensorNotFoundException("No sensor found with the given id!"));
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll()
                .list();
    }

    @Override
    @Transactional
    public Sensor addSensor(Sensor sensor) {
        sensorRepository.persist(sensor);
        return sensor;
    }

}
