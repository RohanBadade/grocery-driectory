package com.demo.grcy.service.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryExceptionMessage {
    private String errorType;
    private String errorCode;
    private String errorMessage;
}
