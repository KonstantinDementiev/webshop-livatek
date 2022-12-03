package com.dementiev.webshop.utils;

import com.dementiev.webshop.exception.InvalidConsoleInputException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    private static final String INPUT_VALUE_IS_NOT_INTEGER_MESSAGE = "Input value [%s] is not Integer";
    private static final String INPUT_VALUE_IS_NOT_DOUBLE_MESSAGE = "Input value [%s] is not Double";

    public static int getIntegerFromString(final String inputString) {
        try {
            return Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            log.error(String.format(INPUT_VALUE_IS_NOT_INTEGER_MESSAGE, inputString));
            throw new InvalidConsoleInputException(String.format(INPUT_VALUE_IS_NOT_INTEGER_MESSAGE, inputString));
        }
    }

    public static double getDoubleFromString(final String inputString) {
        try {
            return Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            log.error(String.format(INPUT_VALUE_IS_NOT_DOUBLE_MESSAGE, inputString));
            throw new InvalidConsoleInputException(String.format(INPUT_VALUE_IS_NOT_DOUBLE_MESSAGE, inputString));
        }
    }
}
