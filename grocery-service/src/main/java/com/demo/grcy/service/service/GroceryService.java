package com.demo.grcy.service.service;

import com.demo.grcy.model.dto.*;

import javax.ws.rs.core.Response;
import java.util.List;

public interface GroceryService {

    /**
     * Register's Grocery item
     *
     * @param registerGroceryItemsRequest object containing grocery details
     * @return RegisterGroceryItemsResponse object containing grocery details
     **/
    RegisterGroceryItemsResponse registerGrocery(RegisterGroceryItemsRequest registerGroceryItemsRequest);

    /**
     * Retrieves list of grocery items.
     *
     * @return list of object containing grocery details
     **/
    List<RetrieveGroceryItemResponse> retrieveGroceryItem();

    /**
     * Deletes a grocery item.
     *
     * @param itemName grocery item name which is to be deleted
     **/
    void deleteGroceryItem(String itemName);

    /**
     * Updates grocery item details.
     *
     * @param itemName                  grocery item name whose details needs to be updated
     * @param updateGroceryItemsRequest object containing update grocery item details
     * @return object containing updated grocery item details
     **/
    UpdateGroceryItemResponse updateGroceryItem(String itemName, UpdateGroceryItemsRequest updateGroceryItemsRequest);

    /**
     * Retrieves a list of available grocery item details.
     *
     * @return list of object containing available grocery item details.
     **/
    List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItem();

    /**
     * Books grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @return object containing booked grocery item details.
     **/
    BookGroceryItemsResponse bookGroceryItems(BookGroceryItemsRequest bookGroceryItemsRequest);
}
