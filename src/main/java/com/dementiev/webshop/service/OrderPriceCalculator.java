package com.dementiev.webshop.service;

import com.dementiev.webshop.entity.Order;
import com.dementiev.webshop.enums.Currency;
import com.dementiev.webshop.enums.ProductType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderPriceCalculator {

    private final FreightPriceCalculator freightPriceCalculator;
    private final PriceOutputFormatter priceOutputFormatter;

    public String calculate(final Order order) {
        double freightPrice = getFreightPrice(order);
        double orderPrice = (order.getAmount() * order.getPrice() + freightPrice);
        return priceOutputFormatter.convertPriceToDecimalFormat(orderPrice) + " " + getCurrency();
    }

    private String getCurrency() {
        return Currency.DEFAULT.getDescription();
    }

    private double getFreightPrice(Order order) {
        if (order.getType() == ProductType.BOOK) {
            return freightPriceCalculator.calculate(order.getAmount());
        }
        return 0.0;
    }

}
