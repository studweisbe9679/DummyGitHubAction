package com.example.application.logic.service;

import com.example.common.exception.BadRequestException;
import com.example.common.exception.NotFoundException;
import com.example.domain.model.Circle;
import com.example.persistence.repository.CircleRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

@ApplicationScoped
public class CircleService implements ICircleService {

    private final CircleRepository circleRepository;

    public CircleService(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
    }

    @Override
    @Transactional
    public void persistCircle(Circle circle) {
        if (circleRepository.findCircleByName(circle.getName()).isPresent())
            throw new BadRequestException("Circle already exits!");

        circleRepository.persist(circle);
    }

    @Override
    public Circle getCircle(String name) {
        return circleRepository.findCircleByName(name)
                .orElseThrow(() -> new NotFoundException("Circle by the given name could not be found!"));
    }

    @Override
    public List<Circle> getAllCircles() {
        return circleRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public double calcCirclePerimeter(String name) {
        var circle = getCircle(name);
        return 2 * PI * circle.getRadius();
    }

    @Override
    public double calcCircleArea(String name) {
        var circle = getCircle(name);
        return PI * pow(circle.getRadius(), 2);
    }
}
