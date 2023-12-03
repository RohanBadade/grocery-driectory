package com.demo.grcy.rest.client;

import com.demo.grcy.model.dto.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/User")
@RegisterRestClient
@ApplicationScoped
public interface UserApi {

    @Path("/Register")
    @POST
    RegisterUserDetailsResponse registerUser(RegisterUserDetailsRequest registerUserDetailsRequest);

    @Path("/Book")
    @POST
    BookGroceryItemsResponse bookGroceryItems(@Valid BookGroceryItemsRequest bookGroceryItemsRequest);

    @Path("/Retrieve")
    @GET
    List<RetrieveGroceryItemResponse> retrieveAvailableGroceryItems();
}
