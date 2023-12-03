package com.demo.grcy.rest.client;

import com.demo.grcy.model.dto.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Grocery")
@RegisterRestClient
@ApplicationScoped
public interface AdminApi {


    @Path("/Register")
    @POST
    RegisterGroceryItemsResponse registerGroceryItems(@Valid RegisterGroceryItemsRequest registerGroceryItemsRequest);

    @Path("/Retrieve")
    @GET
    List<RetrieveGroceryItemResponse> retrieveGroceryItems();

    @Path("/{itemName}/Update")
    @PATCH
    UpdateGroceryItemResponse updateGroceryItem(@PathParam("itemName") String itemName,
                                                @Valid UpdateGroceryItemsRequest updateGroceryItemsRequest);

    @Path("/{itemName}/Delete")
    @DELETE
    Response deleteGroceryItems(@PathParam("itemName") String itemName);
}
