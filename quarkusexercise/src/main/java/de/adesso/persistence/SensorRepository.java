package de.adesso.persistence;

import de.adesso.domain.Sensor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@ApplicationScoped
public class SensorRepository implements PanacheRepository<Sensor> {

}
