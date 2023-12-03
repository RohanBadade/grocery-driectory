package com.demo.grcy.service.mapper;

import com.demo.grcy.model.dto.RegisterUserDetailsRequest;
import com.demo.grcy.model.dto.RegisterUserDetailsResponse;
import com.demo.grcy.service.domain.UserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface UserMapper {


    UserDetails mapToUserDomain(String userId, RegisterUserDetailsRequest registerUserDetailsRequest);


    RegisterUserDetailsResponse mapToUserDetailsResponse(UserDetails userDetails);
}
