package com.demo.grcy.service.resource.impl;

import com.demo.grcy.model.dto.*;
import com.demo.grcy.rest.client.AdminApi;
import com.demo.grcy.service.service.GroceryService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Grocery")
@RegisterRestClient
@ApplicationScoped
public class AdminResourceImpl implements AdminApi {

    @Inject
    GroceryService groceryService;

    @Path("/Register")
    @POST
    @Override
    public RegisterGroceryItemsResponse registerGroceryItems(@Valid RegisterGroceryItemsRequest registerGroceryItemsRequest) {
        return groceryService.registerGrocery(registerGroceryItemsRequest);
    }

    @Override
    public List<RetrieveGroceryItemResponse> retrieveGroceryItems() {
        return groceryService.retrieveGroceryItem();
    }

    @Override
    public UpdateGroceryItemResponse updateGroceryItem(String itemName,
                                                       @Valid UpdateGroceryItemsRequest updateGroceryItemsRequest) {
        return groceryService.updateGroceryItem(itemName, updateGroceryItemsRequest);
    }

    @Override
    public Response deleteGroceryItems(String itemName) {
        return groceryService.deleteGroceryItem(itemName);
    }
}
