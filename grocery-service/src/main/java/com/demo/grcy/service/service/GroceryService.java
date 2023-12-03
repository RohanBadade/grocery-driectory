package com.demo.grcy.service.service;

import com.demo.grcy.model.dto.*;

import javax.ws.rs.core.Response;
import java.util.List;

public interface GroceryService {

    RegisterGroceryItemsResponse registerGrocery(RegisterGroceryItemsRequest registerGroceryItemsRequest);

    List<RetrieveGroceryItemResponse> retrieveGroceryItem();

    Response deleteGroceryItem(String itemName);

    UpdateGroceryItemResponse updateGroceryItem(String itemName, UpdateGroceryItemsRequest updateGroceryItemsRequest);

    List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItem();

    BookGroceryItemsResponse bookGroceryItems(BookGroceryItemsRequest bookGroceryItemsRequest);
}
