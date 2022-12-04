package com.dementiev.webshop.validator;

import com.dementiev.webshop.exception.InvalidInputProductAmountException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class InputProductAmountValidator {

    private static final String INPUT_VALUE_IS_NULL_MESSAGE = "Input product amount is NULL";
    private static final String INPUT_VALUE_IS_ZERO_MESSAGE = "Input product amount is 0";
    private static final String INPUT_VALUE_IS_NEGATIVE_MESSAGE = "Input product amount can not be NEGATIVE";

    public static void validate(final Integer amount) {
        if (Objects.isNull(amount)) {
            log.error(INPUT_VALUE_IS_NULL_MESSAGE);
            throw new InvalidInputProductAmountException(INPUT_VALUE_IS_NULL_MESSAGE);
        } else if (amount == 0) {
            log.warn(INPUT_VALUE_IS_ZERO_MESSAGE);
        } else if (amount < 0) {
            log.error(INPUT_VALUE_IS_NEGATIVE_MESSAGE);
            throw new InvalidInputProductAmountException(INPUT_VALUE_IS_NEGATIVE_MESSAGE);
        }
    }
}
