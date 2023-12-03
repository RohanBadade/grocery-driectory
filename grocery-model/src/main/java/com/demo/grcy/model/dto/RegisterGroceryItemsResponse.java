package com.demo.grcy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(name = "RegisterGroceryItemsResponse")
public class RegisterGroceryItemsResponse {

    @JsonProperty("ItemName")
    @Schema(name = "ItemName", description = "Name of grocery item.")
    private String itemName;
}
