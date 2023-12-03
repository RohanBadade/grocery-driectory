package com.demo.grcy.service.util;

import org.apache.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessValidationExceptionResponse implements ExceptionMapper<BusinessValidationException> {
    @Override
    public Response toResponse(BusinessValidationException e) {
        return Response.status(HttpStatus.SC_UNPROCESSABLE_ENTITY).entity(e.getGroceryExceptionDetails()).build();
    }
}
