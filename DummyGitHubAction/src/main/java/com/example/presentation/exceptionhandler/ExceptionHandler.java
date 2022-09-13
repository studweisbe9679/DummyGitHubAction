package com.example.presentation.exceptionhandler;

import com.example.common.exception.BadRequestException;
import com.example.common.exception.NotFoundException;
import com.example.presentation.dto.response.ServerErrorResponseDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof NotFoundException) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ServerErrorResponseDto(Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
                    .build();
        }
        if(exception instanceof BadRequestException) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new ServerErrorResponseDto(Status.BAD_REQUEST.getStatusCode(), exception.getMessage()))
                    .build();
        }

        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity(new ServerErrorResponseDto(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                        "Something unexpected happened. Try again"))
                .build();
    }

}