package com.demo.grcy.model.dto;

import com.demo.grcy.model.constant.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(name = "RegisterGroceryItemsRequest")
public class RegisterGroceryItemsRequest {

    @NotBlank(message = "Item Name" + ErrorMessages.NOT_PROVIDED)
    @Size(message = "Item Name " + ErrorMessages.MAXIMUM_50_SIZE_EXCEEDS)
    @JsonProperty("ItemName")
    @Schema(name = "ItemName", description = "Name of grocery item.")
    private String itemName;

    @NotNull(message = "Price" + ErrorMessages.NOT_PROVIDED)
    @Min(value = 1, message = "Price " + ErrorMessages.CANNOT_BE_LESS_THAN_ONE)
    @JsonProperty("Price")
    @Schema(name = "Price", description = "Price per unit of grocery item.")
    private int price;

    @NotNull(message = "Quantity" + ErrorMessages.NOT_PROVIDED)
    @Min(value = 1, message = "Quantity" + ErrorMessages.CANNOT_BE_LESS_THAN_ONE)
    @JsonProperty("Quantity")
    @Schema(name = "Quantity", description = "Quantity of grocery item in stock.")
    private int quantity;
}
