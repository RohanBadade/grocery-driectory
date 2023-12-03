package com.demo.grcy.model.dto;

import com.demo.grcy.model.constant.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterUserDetailsRequest {

    @NotBlank(message = "First Name" + ErrorMessages.NOT_PROVIDED)
    @JsonProperty("FirstName")
    @Schema(name = "FirstName", description = "First name of users.")
    private String firstName;

    @NotBlank(message = "Last Name" + ErrorMessages.NOT_PROVIDED)
    @JsonProperty("LastName")
    @Schema(name = "LastName", description = "Last name of users.")
    private String lastName;

    @NotBlank(message = "Email Address" + ErrorMessages.NOT_PROVIDED)
    @Email(message = "Invalid email format.")
    @JsonProperty("Email")
    @Schema(name = "Email", description = "Email Id of users.")
    private String email;
}
