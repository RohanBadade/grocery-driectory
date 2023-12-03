package com.demo.grcy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Data
public class BookGroceryItemsResponse {

    @JsonProperty("ReceiptId")
    @Schema(name = "ReceiptId", description = "Id of receipt.")
    private UUID receiptId;

    private List<PurchasedGroceryItems> purchasedGroceryItemsList;

    @JsonProperty("TotalBillAmount")
    @Schema(name = "TotalBillAmount", description = "Overall bill amount.")
    private int totalBillAmount;
}
