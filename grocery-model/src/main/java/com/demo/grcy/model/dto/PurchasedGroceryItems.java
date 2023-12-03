package com.demo.grcy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class PurchasedGroceryItems {

    @JsonProperty("ItemName")
    @Schema(name = "ItemName", description = "Name of grocery item.")
    private String itemName;

    @JsonProperty("NumberOfUnitsPurchased")
    @Schema(name = "Number Of Units Purchased", description = "Number of units purchased.")
    private int numberOfUnitsPurchased;

    @JsonProperty("TotalPrice")
    @Schema(name = "TotalPrice", description = "Total Price.")
    private int totalPrice;
}
