package com.demo.grcy.service.util;

import com.demo.grcy.service.util.dto.GroceryExceptionDetails;
import com.demo.grcy.service.util.dto.GroceryExceptionMessage;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

    private final GroceryExceptionDetails groceryExceptionDetails = new GroceryExceptionDetails();

    public ResourceNotFoundException(String errorCode, String errorMessage) {
        super(errorMessage);
        GroceryExceptionMessage exceptionMessage = new GroceryExceptionMessage();
        exceptionMessage.setErrorType("Resource Not Found Exception");
        exceptionMessage.setErrorCode(errorCode);
        exceptionMessage.setErrorMessage(errorMessage);
        groceryExceptionDetails.getGroceryExceptionMessageList().add(exceptionMessage);
    }

    public ResourceNotFoundException(List<GroceryExceptionMessage> groceryExceptionMessages) {
        groceryExceptionDetails.setGroceryExceptionMessageList(groceryExceptionMessages);
    }

    public GroceryExceptionDetails getGroceryExceptionDetails() {
        return groceryExceptionDetails;
    }
}

