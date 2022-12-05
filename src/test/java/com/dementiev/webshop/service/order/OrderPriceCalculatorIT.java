package com.dementiev.webshop.service.order;

import com.dementiev.webshop.dto.OrderDto;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.service.freight.FreightPriceCalculatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {OrderPriceCalculatorImpl.class, FreightPriceCalculatorImpl.class})
class OrderPriceCalculatorIT {

    @Autowired
    private OrderPriceCalculatorImpl orderPriceCalculatorImpl;

    @Test
    void calculate_case_1_with_type_BOOK() {
        OrderDto order = OrderDto.builder().amount(0).price(100.0).type(ProductType.BOOK).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(0, actualResult);
    }

    @Test
    void calculate_case_2_with_type_BOOK() {
        OrderDto order = OrderDto.builder().amount(5).price(100.0).type(ProductType.BOOK).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(550.00, actualResult);
    }

    @Test
    void calculate_case_3_with_type_BOOK() {
        OrderDto order = OrderDto.builder().amount(10).price(100.0).type(ProductType.BOOK).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(1050.00, actualResult);
    }

    @Test
    void calculate_case_4_with_type_BOOK() {
        OrderDto order = OrderDto.builder().amount(15).price(100.0).type(ProductType.BOOK).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(1575.00, actualResult);
    }

    @Test
    void calculate_case_5_with_type_ONLINE() {
        OrderDto order = OrderDto.builder().amount(0).price(100.0).type(ProductType.ONLINE).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(0, actualResult);
    }

    @Test
    void calculate_case_6_with_type_ONLINE() {
        OrderDto order = OrderDto.builder().amount(5).price(100.0).type(ProductType.ONLINE).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(500.00, actualResult);
    }

    @Test
    void calculate_case_7_with_type_ONLINE() {
        OrderDto order = OrderDto.builder().amount(10).price(100.0).type(ProductType.ONLINE).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(1000.00, actualResult);
    }

    @Test
    void calculate_case_8_with_type_ONLINE() {
        OrderDto order = OrderDto.builder().amount(15).price(100.0).type(ProductType.ONLINE).build();
        double actualResult = orderPriceCalculatorImpl.calculate(order);
        assertEquals(1500.00, actualResult);
    }

}