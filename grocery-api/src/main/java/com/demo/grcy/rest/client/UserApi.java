package com.demo.grcy.rest.client;

import com.demo.grcy.model.dto.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User Api Interface Class.
 * */
@Path("/User")
@RegisterRestClient
@ApplicationScoped
public interface UserApi {

    /**
     * Registers User details.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return RegisterGroceryItemsResponse object containing user id.
     **/
    @Path("/Register")
    @POST
    Response registerUser(@Valid RegisterUserDetailsRequest registerUserDetailsRequest);

    /**
     * Books grocery items.
     *
     * @param bookGroceryItemsRequest object containing booking details
     * @return object containing booked grocery item details.
     **/
    @Path("/Book")
    @POST
    BookGroceryItemsResponse bookGroceryItems(@Valid BookGroceryItemsRequest bookGroceryItemsRequest);

    /**
     * Retrieves a list of available grocery item details.
     *
     * @return list of object containing available grocery item details.
     **/
    @Path("/Grocery/Retrieve")
    @GET
    List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItems();
}
