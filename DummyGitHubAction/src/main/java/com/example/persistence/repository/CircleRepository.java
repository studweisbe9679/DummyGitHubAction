package com.example.persistence.repository;

import com.example.domain.model.Circle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CircleRepository implements PanacheRepository<Circle> {

    public Optional<Circle> findCircleByName(String name) {
        return find("name", name).singleResultOptional();
    }
}
