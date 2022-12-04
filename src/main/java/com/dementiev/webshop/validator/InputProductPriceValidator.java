package com.dementiev.webshop.validator;

import com.dementiev.webshop.exception.InvalidInputProductPriceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class InputProductPriceValidator {

    private static final String INPUT_VALUE_IS_NULL_MESSAGE = "Input product price is NULL";
    private static final String INPUT_VALUE_IS_ZERO_MESSAGE = "Input product price is 0";
    private static final String INPUT_VALUE_IS_NEGATIVE_MESSAGE = "Input product price can not be NEGATIVE";

    public static void validate(final Double price) {
        if (Objects.isNull(price)) {
            log.error(INPUT_VALUE_IS_NULL_MESSAGE);
            throw new InvalidInputProductPriceException(INPUT_VALUE_IS_NULL_MESSAGE);
        } else if (price == 0) {
            log.warn(INPUT_VALUE_IS_ZERO_MESSAGE);
        } else if (price < 0) {
            log.error(INPUT_VALUE_IS_NEGATIVE_MESSAGE);
            throw new InvalidInputProductPriceException(INPUT_VALUE_IS_NEGATIVE_MESSAGE);
        }
    }
}
