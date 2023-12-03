package com.demo.grcy.model.enumeration;

import lombok.*;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum GroceryStatus {
    IN_STOCK("IN_STOCK"),
    OUT_OF_STOCK("OUT_OF_STOCK");

    private final String value;

    public static Optional<GroceryStatus> getEnum(String value) {
        return Stream.of(GroceryStatus.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }
}
