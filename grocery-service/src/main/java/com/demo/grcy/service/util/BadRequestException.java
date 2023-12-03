package com.demo.grcy.service.util;

import com.demo.grcy.service.util.dto.GroceryExceptionDetails;
import com.demo.grcy.service.util.dto.GroceryExceptionMessage;

import java.util.List;

public class BadRequestException extends RuntimeException {

    private GroceryExceptionDetails groceryExceptionDetails = new GroceryExceptionDetails();

    public BadRequestException(String errorCode, String errorMessage) {
        super(errorMessage);
        GroceryExceptionMessage exceptionMessage = new GroceryExceptionMessage();
        exceptionMessage.setErrorType("Bad Request Exception");
        exceptionMessage.setErrorCode(errorCode);
        exceptionMessage.setErrorMessage(errorMessage);
    }

    public BadRequestException(List<GroceryExceptionMessage> groceryExceptionMessages) {
        groceryExceptionDetails.setGroceryExceptionMessageList(groceryExceptionMessages);
    }

    public GroceryExceptionDetails getGroceryExceptionDetails() {
        return groceryExceptionDetails;
    }
}
