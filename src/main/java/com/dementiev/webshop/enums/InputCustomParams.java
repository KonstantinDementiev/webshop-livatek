package com.dementiev.webshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InputCustomParams {

    INPUT_CURRENCY("input-currency", "DKK"),
    OUTPUT_CURRENCY("output-currency", "DKK"),
    VAT("vat", "NO_COUNTRY");

    private final String description;
    private final String defaultValue;
}
