package com.example.presentation.controller;

import com.example.application.logic.service.ICircleService;
import com.example.common.util.CalcUtils;
import com.example.presentation.dto.request.AreaRequestDTO;
import com.example.presentation.dto.request.CreateCircleRequestDTO;
import com.example.presentation.dto.request.PerimeterRequestDTO;
import com.example.presentation.dto.response.AreaResponseDTO;
import com.example.presentation.dto.response.GetCircleResponseDTO;
import com.example.presentation.dto.response.PerimeterResponseDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.stream.Collectors;


@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/circles")
public class CircleController {

    @Inject
    ICircleService circleService;


    @PUT
    @Path("/circle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCircle(CreateCircleRequestDTO circleDTO) {
        return Response.created(URI.create("/api/v1/sensors/" + circleDTO.getName())).build();
    }

    @GET
    @Path("/circle/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCircle(String name) {
        var circle = circleService.getCircle(name);
        return Response.ok(new GetCircleResponseDTO(circle.getName(), circle.getRadius())).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCircles() {
        var circlesResponse = circleService.getAllCircles().stream()
                .map(c -> new GetCircleResponseDTO(c.getName(), c.getRadius())).collect(Collectors.toList());
        return Response.ok(circlesResponse).build();
    }

    @GET
    @Path("/perimeter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCirclePerimeter(PerimeterRequestDTO circleDto) {
        double perimeter = CalcUtils.Round(circleService.calcCirclePerimeter(circleDto.getName()), circleDto.getDecimals());
        System.out.println("Test: " + Response.ok(new PerimeterResponseDTO(circleDto.getName(), circleDto.getDecimals(), perimeter)).build());
        return Response.ok(new PerimeterResponseDTO(circleDto.getName(), circleDto.getDecimals(), perimeter)).build();
    }

    @GET
    @Path("/area")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCircleArea(AreaRequestDTO circleDto) {
        double area = CalcUtils.Round(circleService.calcCircleArea(circleDto.getName()), circleDto.getDecimals());
        return Response.ok(new AreaResponseDTO(circleDto.getName(), circleDto.getDecimals(), area)).build();
    }

}
