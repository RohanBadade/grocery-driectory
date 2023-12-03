package com.demo.grcy.service.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryExceptionDetails {

    private List<GroceryExceptionMessage> groceryExceptionMessageList = new ArrayList<>();
}
