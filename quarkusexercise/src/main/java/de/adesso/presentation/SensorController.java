package de.adesso.presentation;

import de.adesso.common.ExceptionHandler;
import de.adesso.common.SensorNotFoundException;
import de.adesso.domain.Sensor;
import de.adesso.application.ISensorService;
import lombok.SneakyThrows;
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
import java.util.List;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@RequestScoped
@Path("/api/v1/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorController {

    @Inject
    ISensorService sensorService;

    @Inject
    ISensorMapper sensorMapper;


    @GET
    @Path("/{id}")
    @Operation(summary = "Gets a sensor", description = "Retrieves a sensor by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Sensor.class))),
            @APIResponse(responseCode = "404", description="Sensor not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Sensor getSensor(@PathParam("id") long id) throws SensorNotFoundException {
        return sensorService.getSensor(id);
    }

    @GET
    @Operation(summary = "Gets all sensors", description = "Retrieves all available sensors")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Sensor.class)))
    )
    public Response getAllSensors() {
        return Response.ok(sensorService.getAllSensors()).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Adds a sensor", description = "Creates a sensor and persists into database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sensor successfully added",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Sensor.class))))
    public Response postSensor(SensorDTO sensor) {
        var sensorToAdd = sensorMapper.mapSensorDtoToSensor(sensor);
        return Response.ok(sensorService.addSensor(sensorToAdd), MediaType.APPLICATION_JSON).build();
    }

}
