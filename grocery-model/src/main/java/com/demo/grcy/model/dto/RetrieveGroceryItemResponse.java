package com.demo.grcy.model.dto;

import com.demo.grcy.model.enumeration.GroceryStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class RetrieveGroceryItemResponse {

    @JsonProperty("ItemName")
    @Schema(name = "ItemName", description = "Name of grocery item.")
    private String itemName;

    @JsonProperty("Price")
    @Schema(name = "Price", description = "Price per unit of grocery item.")
    private int itemPrice;

    @JsonProperty("Quantity")
    @Schema(name = "Quantity", description = "Quantity of grocery item in stock.")
    private int quantity;

    @JsonProperty("NumberOfAvailableItems")
    @Schema(name = "Number Of Available Items", description = "Number of available grocery item in stock.")
    private int numberOfAvailableItems;

    @JsonProperty("NumberOfSoldItems")
    @Schema(name = "Number Of Sold Items", description = "Number of sold grocery item.")
    private int numberOfSoldItems;

    @JsonProperty("Status")
    @Schema(name = "Status", description = "Status signifying whether grocery item is in stock or out of stock.")
    private GroceryStatus status;
}
