package com.dementiev.webshop.service;

import com.dementiev.webshop.exception.InvalidInputProductAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FreightPriceCalculatorBookImplTest {

    private FreightPriceCalculator freightPriceCalculator;

    @BeforeEach
    void setUp() {
        freightPriceCalculator = new FreightPriceCalculatorBookImpl();
    }

    @Test
    void calculate_when_inputIsNull_then_throwCustomException() {
        InvalidInputProductAmountException thrown = assertThrows(
                InvalidInputProductAmountException.class,
                () -> freightPriceCalculator.calculate(null),
                "Expected calculate() to throw exception, but it didn't"
        );
        assertEquals("Input ProductAmount is NULL", thrown.getMessage());
    }

    @Test
    void calculate_when_inputIsNotNull_then_returnValue() {
        assertEquals(0.0, freightPriceCalculator.calculate(-1));
        assertEquals(0.0, freightPriceCalculator.calculate(0));
        assertEquals(50.0, freightPriceCalculator.calculate(1));
        assertEquals(50.0, freightPriceCalculator.calculate(10));
        assertEquals(75.0, freightPriceCalculator.calculate(11));
        assertEquals(75.0, freightPriceCalculator.calculate(20));
        assertEquals(100.0, freightPriceCalculator.calculate(21));
    }

}