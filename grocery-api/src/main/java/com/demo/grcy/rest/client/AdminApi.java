package com.demo.grcy.rest.client;

import com.demo.grcy.model.dto.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Admin Api Interface Class.
 * */
@Path("/Admin/Grocery")
@RegisterRestClient
@ApplicationScoped
public interface AdminApi {

    /**
     * Register's Grocery item
     *
     * @param registerGroceryItemsRequest object containing grocery details
     * @return Response object containing grocery item name
     **/
    @Path("/Register")
    @POST
    Response registerGroceryItems(@Valid RegisterGroceryItemsRequest registerGroceryItemsRequest);

    /**
     * Retrieves list of grocery items.
     *
     * @return list of object containing grocery details
     **/
    @Path("/Retrieve")
    @GET
    List<RetrieveGroceryItemResponse> retrieveGroceryItems();

    /**
     * Updates grocery item details.
     *
     * @param itemName                  grocery item name whose details needs to be updated
     * @param updateGroceryItemsRequest object containing update grocery item details
     * @return object containing updated grocery item details
     **/
    @Path("/{itemName}/Update")
    @PATCH
    UpdateGroceryItemResponse updateGroceryItem(@PathParam("itemName") String itemName,
                                                @Valid UpdateGroceryItemsRequest updateGroceryItemsRequest);

    /**
     * Deletes a grocery item.
     *
     * @param itemName grocery item name which is to be deleted
     * @return 204 no content response status.
     **/
    @Path("/{itemName}/Delete")
    @DELETE
    Response deleteGroceryItems(@PathParam("itemName") String itemName);
}
