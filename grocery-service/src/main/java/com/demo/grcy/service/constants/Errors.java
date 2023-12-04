package com.demo.grcy.service.constants;

import com.demo.grcy.model.constant.ErrorCodes;
import com.demo.grcy.model.constant.ErrorMessages;
import com.demo.grcy.service.util.BusinessValidationException;
import com.demo.grcy.service.util.DatabaseException;
import com.demo.grcy.service.util.ResourceNotFoundException;

/**
 * Error class containing error details
 **/
public class Errors {

    /**
     * Throws database exception when there is an error while persisting grocery items.
     *
     * @return DatabaseException with error message and error code.
     **/
    public static DatabaseException failedToPersistGroceryItems() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_REGISTER_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_REGISTER_GROCERY_ITEM);
    }

    /**
     * Throws database exception when there is an error while persisting user details.
     *
     * @return DatabaseException with error message and error code.
     **/
    public static DatabaseException failedToPersistUserDetails() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_REGISTER_USER_DETAILS,
                ErrorMessages.PERSISTENCE_ERROR_DURING_REGISTER_USER_DETAILS);
    }

    /**
     * Throws database exception when there is an error while retrieving grocery item details.
     *
     * @return DatabaseException with error message and error code.
     **/
    public static DatabaseException failedToRetrieveGroceryItem() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM);
    }

    /**
     * Throws database exception when there is an error while deleting grocery item details.
     *
     * @return DatabaseException with error message and error code.
     **/
    public static DatabaseException failedToDeleteGroceryItem() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_DELETING_GROCERY_ITEM,
                ErrorMessages.PERSISTENCE_ERROR_DURING_DELETING_GROCERY_ITEM);
    }

    /**
     * Throws database exception when there is an error while retrieving list of grocery item details.
     *
     * @return DatabaseException with error message and error code.
     **/
    public static DatabaseException failedToRetrieveGroceryItemList() {
        return new DatabaseException(ErrorCodes.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM_LIST,
                ErrorMessages.PERSISTENCE_ERROR_DURING_RETRIEVE_GROCERY_ITEM_LIST);
    }

    /**
     * Throws business validation exception when grocery item already exist.
     *
     * @return BusinessValidationException with error message and error code.
     **/
    public static BusinessValidationException duplicateGroceryItem(String itemName) {
        return new BusinessValidationException(ErrorCodes.GROCERY_ITEM_ALREADY_EXIST,
                String.format(ErrorMessages.GROCERY_ITEM_ALREADY_EXIST, itemName));
    }

    /**
     * Throws resource not found exception when grocery item does not exist.
     *
     * @return ResourceNotFoundException with error message and error code.
     **/
    public static ResourceNotFoundException groceryItemDoesNotExist(String itemName) {
        return new ResourceNotFoundException(ErrorCodes.GROCERY_ITEM_DOES_NOT_EXIST,
                String.format(ErrorMessages.GROCERY_ITEM_DOES_NOT_EXIST, itemName));
    }
}
