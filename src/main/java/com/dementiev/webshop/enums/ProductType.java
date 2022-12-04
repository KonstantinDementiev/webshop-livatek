package com.dementiev.webshop.enums;

import com.dementiev.webshop.exception.InvalidProductTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    ONLINE("online"),
    BOOK("book"),
    ALL("all");

    private static final String INVALID_TYPE_MESSAGE = "Input ProductType [%s] does not exist";
    private final String description;

    public static ProductType getValue(final String inputDescription) {
        return Arrays.stream(ProductType.values())
                .filter(type -> inputDescription.equalsIgnoreCase(type.getDescription()))
                .findAny()
                .orElseThrow(() -> new InvalidProductTypeException(
                        String.format(INVALID_TYPE_MESSAGE, inputDescription)));
    }
}
