package com.dementiev.webshop.service;

import com.dementiev.webshop.entity.Order;
import com.dementiev.webshop.enums.ProductType;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderPriceCalculatorIT {

    private OrderPriceCalculator orderPriceCalculator;

    @BeforeEach
    void setUp() {
        BasicConfigurator.configure();
        FreightPriceCalculator freightPriceCalculator = new FreightPriceCalculatorBookImpl();
        PriceOutputFormatter priceOutputFormatter = new PriceOutputFormatter();
        orderPriceCalculator = new OrderPriceCalculator(freightPriceCalculator, priceOutputFormatter);
    }

    @Test
    void calculate_case_1_with_type_BOOK() {
        Order order = Order.builder().amount(0).price(100.0).type(ProductType.BOOK).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("0 DKK", actualResult);
    }

    @Test
    void calculate_case_2_with_type_BOOK() {
        Order order = Order.builder().amount(5).price(100.0).type(ProductType.BOOK).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("550.00 DKK", actualResult);
    }

    @Test
    void calculate_case_3_with_type_BOOK() {
        Order order = Order.builder().amount(10).price(100.0).type(ProductType.BOOK).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("1050.00 DKK", actualResult);
    }

    @Test
    void calculate_case_4_with_type_BOOK() {
        Order order = Order.builder().amount(15).price(100.0).type(ProductType.BOOK).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("1575.00 DKK", actualResult);
    }

    @Test
    void calculate_case_5_with_type_ONLINE() {
        Order order = Order.builder().amount(0).price(100.0).type(ProductType.ONLINE).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("0 DKK", actualResult);
    }

    @Test
    void calculate_case_6_with_type_ONLINE() {
        Order order = Order.builder().amount(5).price(100.0).type(ProductType.ONLINE).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("500.00 DKK", actualResult);
    }

    @Test
    void calculate_case_7_with_type_ONLINE() {
        Order order = Order.builder().amount(10).price(100.0).type(ProductType.ONLINE).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("1000.00 DKK", actualResult);
    }

    @Test
    void calculate_case_8_with_type_ONLINE() {
        Order order = Order.builder().amount(15).price(100.0).type(ProductType.ONLINE).build();
        String actualResult = orderPriceCalculator.calculate(order);
        assertEquals("1500.00 DKK", actualResult);
    }

}