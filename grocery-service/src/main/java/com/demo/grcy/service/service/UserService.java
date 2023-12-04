package com.demo.grcy.service.service;

import com.demo.grcy.model.dto.RegisterUserDetailsRequest;
import com.demo.grcy.model.dto.RegisterUserDetailsResponse;

/**
 * User Service Interface Class.
 * */
public interface UserService {

    /**
     * Registers User details.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return RegisterGroceryItemsResponse object containing user id.
     **/
    RegisterUserDetailsResponse registerUser(RegisterUserDetailsRequest registerUserDetailsRequest);
}
