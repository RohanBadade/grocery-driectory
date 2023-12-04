package com.demo.grcy.service.service.impl;

import com.demo.grcy.model.dto.RegisterUserDetailsRequest;
import com.demo.grcy.model.dto.RegisterUserDetailsResponse;
import com.demo.grcy.service.constants.Errors;
import com.demo.grcy.service.domain.UserDetails;
import com.demo.grcy.service.mapper.UserMapper;
import com.demo.grcy.service.repository.UserRepository;
import com.demo.grcy.service.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Random;

/**
 * User Service Implementation class implementing UserService interface.
 * */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserMapper userMapper;

    @Inject
    UserRepository userRepository;

    /**
     * Registers User details.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return RegisterGroceryItemsResponse object containing user id.
     **/
    @Override
    @Transactional
    public RegisterUserDetailsResponse registerUser(RegisterUserDetailsRequest registerUserDetailsRequest) {
        String userId = generateUserId(registerUserDetailsRequest);
        UserDetails userDetails = persistUserDetails(userId, registerUserDetailsRequest);
        return userMapper.mapToUserDetailsResponse(userDetails);
    }

    /**
     * Persists User details.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return domain object containing persisted user details.
     **/
    private UserDetails persistUserDetails(String userId, RegisterUserDetailsRequest registerUserDetailsRequest) {
        UserDetails userDetails = userMapper.mapToUserDomain(userId, registerUserDetailsRequest);
        try {
            userRepository.persist(userDetails);
        } catch (PersistenceException exception) {
            throw Errors.failedToPersistUserDetails();
        }
        return userDetails;
    }

    /**
     * Generates a user identifier based on first name and last name.
     *
     * @param registerUserDetailsRequest object containing user details
     * @return generated user id.
     **/
    private String generateUserId(RegisterUserDetailsRequest registerUserDetailsRequest) {
        return registerUserDetailsRequest.getFirstName().substring(0,3) +
                registerUserDetailsRequest.getLastName().substring(0,3) +
                new Random().nextInt(4);
    }
}
