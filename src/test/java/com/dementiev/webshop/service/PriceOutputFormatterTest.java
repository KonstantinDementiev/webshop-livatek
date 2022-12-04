package com.dementiev.webshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {PriceOutputFormatter.class})
class PriceOutputFormatterTest {

    @Autowired
    private PriceOutputFormatter formatter;

    @Test
    void convertPriceToDecimalFormat_when_inputZero_then_returnZero(){
       String actualResult = formatter.convertPriceToDecimalFormat(0.0);
       assertEquals("0", actualResult);
    }

    @Test
    void convertPriceToDecimalFormat_when_inputNumberWithOneDigit_then_returnNumberWithTwoDigits(){
        String actualResult = formatter.convertPriceToDecimalFormat(5.0);
        assertEquals("5.00", actualResult);
    }

    @Test
    void convertPriceToDecimalFormat_when_inputNumberWithManyDigits_then_returnNumberWithTwoDigits(){
        String actualResult = formatter.convertPriceToDecimalFormat(235.0569897);
        assertEquals("235.06", actualResult);
    }
}