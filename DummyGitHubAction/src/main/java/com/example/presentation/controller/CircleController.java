package com.example.presentation.controller;

import com.example.application.logic.service.ICircleService;
import com.example.common.util.CalcUtils;
import com.example.domain.model.Circle;
import com.example.presentation.dto.request.AreaRequestDTO;
import com.example.presentation.dto.request.CreateCircleRequestDTO;
import com.example.presentation.dto.request.PerimeterRequestDTO;
import com.example.presentation.dto.response.AreaResponseDTO;
import com.example.presentation.dto.response.GetCircleResponseDTO;
import com.example.presentation.dto.response.PerimeterResponseDTO;
import com.example.presentation.dto.response.ServerErrorResponseDto;
import com.example.presentation.exceptionhandler.ExceptionHandler;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.stream.Collectors;


@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/circles")
public class CircleController {

    private final ICircleService circleService;

    @Inject
    public CircleController(ICircleService circleService) {
        this.circleService = circleService;
    }

    @PUT
    @Path("/circle")
    @Operation(summary = "Adds a new circle", description = "Creates a new sensor")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetCircleResponseDTO.class))),
            @APIResponse(responseCode = "400", description="The request could not be understood by the server due invalid input",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class))),
            @APIResponse(responseCode = "500", description="The server encountered an internal error and was unable to complete the request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class)))
    })
    public Response createCircle(CreateCircleRequestDTO circleDTO) {
        return Response.created(URI.create("/api/v1/sensors/" + circleDTO.getName())).build();
    }

    @GET
    @Path("/circle/{name}")
    @Operation(summary = "Gets a circle", description = "Retrieves a circle by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetCircleResponseDTO.class))),
            @APIResponse(responseCode = "404", description="Circle by the given name couldn't be found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class))),
            @APIResponse(responseCode = "500", description="The server encountered an internal error and was unable to complete the request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class)))
    })
    public Response getCircle(String name) {
        var circle = circleService.getCircle(name);
        return Response.ok(new GetCircleResponseDTO(circle.getName(), circle.getRadius())).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Gets all circles", description = "Retrieves all circles")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetCircleResponseDTO.class))),
            @APIResponse(responseCode = "500", description="The server encountered an internal error and was unable to complete the request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class)))
    })
    public Response getAllCircles() {
        var circlesResponse = circleService.getAllCircles().stream()
                .map(c -> new GetCircleResponseDTO(c.getName(), c.getRadius())).collect(Collectors.toList());
        return Response.ok(circlesResponse).build();
    }

    @GET
    @Path("/perimeter")
    @Operation(summary = "Gets a circle's perimeter", description = "Retrieves a circle's perimeter by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PerimeterResponseDTO.class))),
            @APIResponse(responseCode = "404", description="Circle by the given name couldn't be found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class))),
            @APIResponse(responseCode = "500", description="The server encountered an internal error and was unable to complete the request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class)))
    })
    public Response getCirclePerimeter(PerimeterRequestDTO circleDto) {
        double perimeter = CalcUtils.Round(circleService.calcCirclePerimeter(circleDto.getName()), circleDto.getDecimals());
        return Response.ok(new PerimeterResponseDTO(circleDto.getName(), circleDto.getDecimals(), perimeter)).build();
    }

    @GET
    @Operation(summary = "Gets a circle's area", description = "Retrieves a circle's area by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AreaResponseDTO.class))),
            @APIResponse(responseCode = "404", description="Circle by the given name couldn't be found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class))),
            @APIResponse(responseCode = "500", description="The server encountered an internal error and was unable to complete the request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ServerErrorResponseDto.class)))
    })
    @Path("/area")
    public Response getCircleArea(AreaRequestDTO circleDto) {
        double area = CalcUtils.Round(circleService.calcCircleArea(circleDto.getName()), circleDto.getDecimals());
        return Response.ok(new AreaResponseDTO(circleDto.getName(), circleDto.getDecimals(), area)).build();
    }

}
