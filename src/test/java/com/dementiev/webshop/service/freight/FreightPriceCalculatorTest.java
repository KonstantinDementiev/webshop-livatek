package com.dementiev.webshop.service.freight;

import com.dementiev.webshop.exception.InvalidInputProductAmountException;
import com.dementiev.webshop.service.freight.FreightPriceCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FreightPriceCalculatorTest {

    private FreightPriceCalculatorImpl freightPriceCalculator;

    @BeforeEach
    void setUp() {
        freightPriceCalculator = new FreightPriceCalculatorImpl();
    }

    @Test
    void calculate_when_inputIsNull_then_throwCustomException() {
        InvalidInputProductAmountException thrown = assertThrows(
                InvalidInputProductAmountException.class,
                () -> freightPriceCalculator.calculate(null),
                "Expected calculate() to throw exception, but it didn't"
        );
        assertEquals("Input product amount is NULL", thrown.getMessage());
    }

    @Test
    void calculate_when_inputIsNegative_then_throwCustomException() {
        InvalidInputProductAmountException thrown = assertThrows(
                InvalidInputProductAmountException.class,
                () -> freightPriceCalculator.calculate(-1),
                "Expected calculate() to throw exception, but it didn't"
        );
        assertEquals("Input product amount can not be NEGATIVE", thrown.getMessage());
    }

    @Test
    void calculate_when_inputIsNotNull_then_returnValue() {
        assertEquals(0.0, freightPriceCalculator.calculate(0));
        assertEquals(50.0, freightPriceCalculator.calculate(1));
        assertEquals(50.0, freightPriceCalculator.calculate(10));
        assertEquals(75.0, freightPriceCalculator.calculate(11));
        assertEquals(75.0, freightPriceCalculator.calculate(20));
        assertEquals(100.0, freightPriceCalculator.calculate(21));
    }

}