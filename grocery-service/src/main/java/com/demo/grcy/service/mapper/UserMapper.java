package com.demo.grcy.service.mapper;

import com.demo.grcy.model.dto.RegisterUserDetailsRequest;
import com.demo.grcy.model.dto.RegisterUserDetailsResponse;
import com.demo.grcy.service.domain.UserDetails;
import org.mapstruct.Mapper;

/**
 * User Mapper Class.
 * */
@Mapper(componentModel = "cdi")
public interface UserMapper {

    /**
     * Maps RegisterUserDetailsRequest dto to UserDetails domain.
     *
     * @param registerUserDetailsRequest dto object containing register user detail
     * @return UserDetails domain object containing user details
     **/
    UserDetails mapToUserDomain(String userId, RegisterUserDetailsRequest registerUserDetailsRequest);

    /**
     * Maps UserDetails domain to RegisterUserDetailsResponse dto.
     *
     * @param userDetails domain object containing user detail
     * @return RegisterUserDetailsResponse dto object containing registered user details
     **/
    RegisterUserDetailsResponse mapToUserDetailsResponse(UserDetails userDetails);
}
