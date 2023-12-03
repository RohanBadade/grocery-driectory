package com.demo.grcy.service.util;

import com.demo.grcy.service.util.dto.GroceryExceptionMessage;
import org.jboss.resteasy.api.validation.ViolationReport;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ExceptionInterceptor implements WriterInterceptor {
    @Override
    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
        if(writerInterceptorContext.getEntity() instanceof ViolationReport) {
            ViolationReport report = (ViolationReport) writerInterceptorContext.getEntity();
            List<GroceryExceptionMessage> messageList = new ArrayList<>();

            if(!report.getParameterViolations().isEmpty()) {
                report.getParameterViolations().stream().forEach(resteasyConstraintViolation -> {
                    GroceryExceptionMessage message = new GroceryExceptionMessage();
                    message.setErrorType("REQUEST PARAMETER VALIDATION ERROR");
                    message.setErrorCode(ExceptionUtils.getErrorCode("GRCY"));
                    message.setErrorMessage(resteasyConstraintViolation.getMessage());
                    messageList.add(message);
                });
            }

            if(!report.getClassViolations().isEmpty()) {
                report.getClassViolations().stream().forEach(resteasyConstraintViolation -> {
                    GroceryExceptionMessage message = new GroceryExceptionMessage();
                    message.setErrorMessage(resteasyConstraintViolation.getMessage());
                    messageList.add(message);
                });
            }

            if(!report.getPropertyViolations().isEmpty()) {
                report.getPropertyViolations().stream().forEach(resteasyConstraintViolation -> {
                    GroceryExceptionMessage message = new GroceryExceptionMessage();
                    message.setErrorMessage(resteasyConstraintViolation.getMessage());
                    messageList.add(message);
                });
            }

            if(!report.getReturnValueViolations().isEmpty()) {
                report.getReturnValueViolations().stream().forEach(resteasyConstraintViolation -> {
                    GroceryExceptionMessage message = new GroceryExceptionMessage();
                    message.setErrorMessage(resteasyConstraintViolation.getMessage());
                    messageList.add(message);
                });
            }

            writerInterceptorContext.setEntity(new BadRequestException(messageList).getGroceryExceptionDetails());
        }
        writerInterceptorContext.proceed();
    }
}
