package com.demo.grcy.service.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User Details domain class
 **/
@Entity(name = "user_details")
@Data
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "is_email_validated")
    private boolean isEmailValidated;
}
