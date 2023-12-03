package com.demo.grcy.model.dto;

import com.demo.grcy.model.constant.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

@Data
public class UpdateGroceryItemsRequest {

    @Min(value = 1, message = "Price " + ErrorMessages.CANNOT_BE_LESS_THAN_ONE)
    @JsonProperty("Price")
    @Schema(name = "Price", description = "Price per unit of grocery item.")
    private int price;

    @Min(value = 1, message = "Quantity " + ErrorMessages.CANNOT_BE_LESS_THAN_ONE)
    @JsonProperty("Quantity")
    @Schema(name = "Quantity", description = "Quantity of grocery item in stock.")
    private int quantity;

    @AssertTrue(message = "Please provide at least one parameter for update.")
    public boolean isAtlestOneParameterPresent() {
        return getPrice() > 0 || getQuantity() > 0;
    }
}