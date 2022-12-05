package com.dementiev.webshop.service.order;

import com.dementiev.webshop.dto.OrderDto;

import java.util.Map;

public interface OrderPriceCalculator {

    double calculate(OrderDto orderDto);
    double calculate(OrderDto orderDto, Map<String, String> customParams);

}
