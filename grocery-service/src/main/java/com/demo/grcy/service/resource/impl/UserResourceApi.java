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
import java.util.List;

@Path("/User")
@RegisterRestClient
@ApplicationScoped
public class UserResourceApi implements UserApi {

    @Inject
    GroceryService groceryService;

    @Inject
    UserService userService;

    @Path("/Register")
    @POST
    @Override
    public RegisterUserDetailsResponse registerUser(RegisterUserDetailsRequest registerUserDetailsRequest) {
        return userService.registerUser(registerUserDetailsRequest);
    }

    @Path("/Book")
    @POST
    public BookGroceryItemsResponse bookGroceryItems(@Valid BookGroceryItemsRequest bookGroceryItemsRequest) {
        return groceryService.bookGroceryItems(bookGroceryItemsRequest);
    }

    @Path("/Retrieve")
    @GET
    @Override
    public List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItems() {
        return groceryService.retrieveAvailableGroceryItem();
    }
}
