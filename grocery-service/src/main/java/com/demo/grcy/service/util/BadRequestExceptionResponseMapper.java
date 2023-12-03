package com.demo.grcy.service.util;

import org.apache.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionResponseMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(e.getGroceryExceptionDetails()).build();
    }
}
