package com.demo.grcy.model.convertor;

import com.demo.grcy.model.enumeration.GroceryStatus;

import javax.persistence.AttributeConverter;
import java.util.Objects;
import java.util.stream.Stream;

public class GroceryStatusConvertor implements AttributeConverter<GroceryStatus, String> {
    @Override
    public String convertToDatabaseColumn(GroceryStatus groceryStatus) {
        return Objects.isNull(groceryStatus) ? null : groceryStatus.getValue();
    }

    @Override
    public GroceryStatus convertToEntityAttribute(String value) {
        if (null == value || value.isEmpty()) {
            return null;
        }

        return Stream.of(GroceryStatus.values())
                .filter(groceryStatus -> groceryStatus.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
