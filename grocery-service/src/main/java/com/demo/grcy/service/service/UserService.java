package com.demo.grcy.service.service;

import com.demo.grcy.model.dto.RegisterUserDetailsRequest;
import com.demo.grcy.model.dto.RegisterUserDetailsResponse;

public interface UserService {
    RegisterUserDetailsResponse registerUser(RegisterUserDetailsRequest registerUserDetailsRequest);
}
