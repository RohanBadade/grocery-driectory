package com.demo.grcy.service.resource.impl;

import com.demo.grcy.model.dto.*;
import com.demo.grcy.rest.client.UserApi;
import com.demo.grcy.service.service.GroceryService;
import com.demo.grcy.service.service.UserService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Implementation class for User Api Interface
 * */
@Path("/User")
@RegisterRestClient
@ApplicationScoped
public class UserResourceApi implements UserApi {

    @Inject
    GroceryService groceryService;

    @Inject
    UserService userService;

    /**
     * Registers User details.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return RegisterGroceryItemsResponse object containing user id.
     **/
    @Path("/Register")
    @POST
    @Override
    public Response registerUser(@Valid RegisterUserDetailsRequest registerUserDetailsRequest) {
        return Response.status(Response.Status.CREATED).entity(userService.registerUser(registerUserDetailsRequest)).build();
    }

    /**
     * Books grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @return object containing booked grocery item details.
     **/
    @Path("/Book")
    @POST
    public BookGroceryItemsResponse bookGroceryItems(@Valid BookGroceryItemsRequest bookGroceryItemsRequest) {
        return groceryService.bookGroceryItems(bookGroceryItemsRequest);
    }

    /**
     * Retrieves a list of available grocery item details.
     *
     * @return list of object containing available grocery item details.
     **/
    @Path("/Grocery/Retrieve")
    @GET
    @Override
    public List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItems() {
        return groceryService.retrieveAvailableGroceryItem();
    }
}
