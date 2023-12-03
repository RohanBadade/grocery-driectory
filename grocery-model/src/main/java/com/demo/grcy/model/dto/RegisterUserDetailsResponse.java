package com.demo.grcy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class RegisterUserDetailsResponse {

    @JsonProperty("UserId")
    @Schema(name = "UserId", description = "Id of users.")
    private String userId;

    @JsonProperty("FirstName")
    @Schema(name = "FirstName", description = "First name of users.")
    private String firstName;
}
