package de.adesso.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@Data
@Entity
public class Sensor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String location;

}
