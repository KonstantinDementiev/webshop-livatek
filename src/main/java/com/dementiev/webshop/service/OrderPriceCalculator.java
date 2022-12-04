package com.dementiev.webshop.service;

import com.dementiev.webshop.dto.OrderDto;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.validator.InputProductPriceValidator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class OrderPriceCalculator {

    @Autowired
    private FreightPriceCalculator freightPriceCalculator;

    public double calculate(final OrderDto orderDto) {
        InputProductPriceValidator.validate(orderDto.price());
        double freightPrice = getFreightPrice(orderDto);
        return (orderDto.amount() * orderDto.price() + freightPrice);
    }

    private double getFreightPrice(OrderDto orderDto) {
        if (orderDto.type() == ProductType.BOOK) {
            return freightPriceCalculator.calculate(orderDto.amount());
        }
        return 0.0;
    }
}
