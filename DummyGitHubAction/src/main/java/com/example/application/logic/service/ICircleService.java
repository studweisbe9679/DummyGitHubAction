package com.example.application.logic.service;

import com.example.domain.model.Circle;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


public interface ICircleService {


    void persistCircle(Circle circle);
    Circle getCircle(String name);

    List<Circle> getAllCircles();
    double calcCirclePerimeter(String name);
    double calcCircleArea(String name);

}
