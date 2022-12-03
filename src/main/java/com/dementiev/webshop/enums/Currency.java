package com.dementiev.webshop.enums;

import com.dementiev.webshop.exception.InvalidCurrencyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Currency {

    DEFAULT("DKK");

    private static final String INVALID_TYPE_MESSAGE = "Input Currency [%s] does not exist";
    private final String description;

    public static Currency getValue(final String inputDescription) {
        return Arrays.stream(Currency.values())
                .filter(type -> inputDescription.equalsIgnoreCase(type.getDescription()))
                .findAny()
                .orElseThrow(() -> new InvalidCurrencyException(
                        String.format(INVALID_TYPE_MESSAGE, inputDescription)));
    }
}
