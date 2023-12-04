package com.demo.grcy.service.resource.impl;

import com.demo.grcy.model.dto.*;
import com.demo.grcy.rest.client.AdminApi;
import com.demo.grcy.service.service.GroceryService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Implementation class for Admin Api Interface
 * */
@Path("/Admin/Grocery")
@RegisterRestClient
@ApplicationScoped
public class AdminResourceImpl implements AdminApi {

    @Inject
    GroceryService groceryService;

    /**
     * Register's Grocery item
     *
     * @param registerGroceryItemsRequest object containing grocery details
     * @return Response object containing grocery item name
     **/
    @Path("/Register")
    @POST
    @Override
    public Response registerGroceryItems(@Valid RegisterGroceryItemsRequest registerGroceryItemsRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(groceryService.registerGrocery(registerGroceryItemsRequest))
                .build();
    }

    /**
     * Retrieves list of grocery items.
     *
     * @return list of object containing grocery details
     **/
    @Path("/Retrieve")
    @GET
    @Override
    public List<RetrieveGroceryItemResponse> retrieveGroceryItems() {
        return groceryService.retrieveGroceryItem();
    }

    /**
     * Updates grocery item details.
     *
     * @param itemName                  grocery item name whose details needs to be updated
     * @param updateGroceryItemsRequest object containing update grocery item details
     * @return object containing updated grocery item details
     **/
    @Path("/{itemName}/Update")
    @PATCH
    @Override
    public UpdateGroceryItemResponse updateGroceryItem(@PathParam("itemName") String itemName,
                                                       @Valid UpdateGroceryItemsRequest updateGroceryItemsRequest) {
        return groceryService.updateGroceryItem(itemName, updateGroceryItemsRequest);
    }

    /**
     * Deletes a grocery item.
     *
     * @param itemName grocery item name which is to be deleted
     * @return 204 no content response status.
     **/
    @Path("/{itemName}/Delete")
    @DELETE
    @Override
    public Response deleteGroceryItems(@PathParam("itemName") String itemName) {
        groceryService.deleteGroceryItem(itemName);
        return Response.noContent().build();
    }
}
