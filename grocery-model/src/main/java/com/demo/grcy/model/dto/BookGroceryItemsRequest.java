package com.demo.grcy.model.dto;

import com.demo.grcy.model.constant.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Schema(name = "BookGroceryItemsRequest")
public class BookGroceryItemsRequest {

    @NotBlank(message = "User Id" + ErrorMessages.NOT_PROVIDED)
    @JsonProperty("UserId")
    @Schema(name = "UserId", description = "Id of users.")
    private String userId;

    @JsonProperty("SendOrderDetailsOnEmail")
    @Schema(name = "SendOrderDetailsOnEmail", description = "Signifies whether to send email or not.")
    private String sendOrderDetailsOnEmail;

    @Valid
    @JsonProperty("GroceryItems")
    private List<GroceryItems> groceryItemsList;
}
