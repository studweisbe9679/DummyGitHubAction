package com.example.application.logic.service;

import com.example.domain.model.Circle;
import com.example.persistence.repository.CircleRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.runtime.PanacheQueryImpl;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CircleServiceTest {

    @Inject
    CircleRepository mock;

    @Inject
    CircleService circleService;

    Circle circle1 = new Circle(UUID.randomUUID(), "TestCircle1", 1);
    Circle circle2 = new Circle(UUID.randomUUID(), "TestCircle2", 2);

    @BeforeEach
    void setUp() {
        mock = Mockito.mock(CircleRepository.class);
    }

    @Test
    void getCircle() {
        Mockito.when(mock.findCircleByName("TestCircle1"))
                .thenReturn(Optional.of(circle1));
        QuarkusMock.installMockForType(mock, CircleRepository.class);

        assertEquals(circleService.getCircle("TestCircle1"), circle1);
    }

    @Test
    void calcCirclePerimeter() {
        Mockito.when(mock.findCircleByName("TestCircle1"))
                .thenReturn(Optional.of(circle1));
        QuarkusMock.installMockForType(mock, CircleRepository.class);

        assertEquals(circleService.calcCirclePerimeter("TestCircle1"), 6.2832, 4);
    }

    @Test
    void calcCircleArea() {
        Mockito.when(mock.findCircleByName("TestCircle1"))
                .thenReturn(Optional.of(circle1));
        QuarkusMock.installMockForType(mock, CircleRepository.class);

        assertEquals(circleService.calcCircleArea("TestCircle1"), 3.1416, 4);
    }
}