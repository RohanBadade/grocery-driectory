package com.demo.grcy.service.constants;

import com.demo.grcy.model.constant.ErrorCodes;
import com.demo.grcy.model.constant.ErrorMessages;
import com.demo.grcy.service.util.BusinessValidationException;
import com.demo.grcy.service.util.DatabaseException;
import com.demo.grcy.service.util.ResourceNotFoundException;

public class Errors {

    public static DatabaseException failedToPersistGroceryItems() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_REGISTER_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_REGISTER_GROCERY_ITEM);
    }

    public static DatabaseException failedToPersistUserDetails() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_REGISTER_USER_DETAILS,
                ErrorMessages.PERSISTENCE_ERROR_DURING_REGISTER_USER_DETAILS);
    }

    public static DatabaseException failedToRetrieveGroceryItem() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM);
    }

    public static DatabaseException failedToDeleteGroceryItem() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_DELETING_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_DELETING_GROCERY_ITEM);
    }

    public static DatabaseException failedToRetrieveGroceryItemList() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM_LIST,
                ErrorMessages.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM_LIST);
    }

    public static BusinessValidationException duplicateGroceryItem(String itemName) {
        return new BusinessValidationException(ErrorCodes.GROCERY_ITEM_ALREADY_EXIST,
                String.format(ErrorMessages.GROCERY_ITEM_ALREADY_EXIST, itemName));
    }

    public static ResourceNotFoundException groceryItemDoesNotExist(String itemName) {
        return new ResourceNotFoundException(ErrorCodes.GROCERY_ITEM_DOES_NOT_EXIST,
                String.format(ErrorMessages.GROCERY_ITEM_DOES_NOT_EXIST, itemName));
    }
}
