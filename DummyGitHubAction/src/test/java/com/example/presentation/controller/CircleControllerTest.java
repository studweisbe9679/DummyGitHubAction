package com.example.presentation.controller;

import com.example.application.logic.service.ICircleService;
import com.example.domain.model.Circle;
import com.example.presentation.dto.request.AreaRequestDTO;
import com.example.presentation.dto.request.CreateCircleRequestDTO;
import com.example.presentation.dto.request.PerimeterRequestDTO;
import com.example.presentation.dto.response.AreaResponseDTO;
import com.example.presentation.dto.response.GetCircleResponseDTO;
import com.example.presentation.dto.response.PerimeterResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CircleControllerTest {

    @Inject
    ICircleService circleService;

    Jsonb jsonb = JsonbBuilder.create();

    Circle circle1 = new Circle(UUID.randomUUID(), "TestCircle1", 1);
    Circle circle2 = new Circle(UUID.randomUUID(), "TestCircle2", 2);


    @BeforeAll
    void setup() {
        circleService.persistCircle(circle1);
        circleService.persistCircle(circle2);
    }

    @Test
    @Order(2)
    void createCircle() {
        var requestDto = new CreateCircleRequestDTO("TestCreateCircle", 2);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonb.toJson(requestDto))
                .when().put("api/v1/circles/circle")
                .then()
                .statusCode(201);
    }

    @Test
    void getCircle() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().get("api/v1/circles/circle/TestCircle1")
                .then()
                .statusCode(200)
                .body(is(jsonb.toJson(new GetCircleResponseDTO("TestCircle1", 1))));
    }

    @Test
    @Order(1)
    void getAllCircles() {
        var resultDto1 = new GetCircleResponseDTO("TestCircle1", 1);
        var resultDto2 = new GetCircleResponseDTO("TestCircle2", 2);

        given()
                .when().get("api/v1/circles")
                .then()
                .statusCode(200)
                .body(is(jsonb.toJson(List.of(resultDto1, resultDto2))));
    }

    @Test
    void getCirclePerimeter() {
        var requestDTO4Decimals = new PerimeterRequestDTO("TestCircle1", 4);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonb.toJson(requestDTO4Decimals))
                .when().get("api/v1/circles/perimeter")
                .then()
                .statusCode(200)
                .body(is(jsonb.toJson(new PerimeterResponseDTO("TestCircle1", 4, 6.2832))));
    }

    @Test
    void getCircleArea() {
        var requestDTO4Decimals = new AreaRequestDTO("TestCircle1", 4);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonb.toJson(requestDTO4Decimals))
                .when().get("api/v1/circles/area")
                .then()
                .statusCode(200)
                .body(is(jsonb.toJson(new AreaResponseDTO("TestCircle1", 4, 3.1416))));
    }
}