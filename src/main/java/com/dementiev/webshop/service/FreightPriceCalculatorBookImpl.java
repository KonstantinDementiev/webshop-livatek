package com.dementiev.webshop.service;

import com.dementiev.webshop.enums.FreightRate;
import com.dementiev.webshop.validator.InputProductAmountValidator;

public class FreightPriceCalculatorBookImpl implements FreightPriceCalculator {

    public Double calculate(final Integer productAmount) {
        InputProductAmountValidator.validate(productAmount);
        if (productAmount <= 0) {
            return 0.0;
        } else if (productAmount <= FreightRate.BASE.getProductAmount()) {
            return FreightRate.BASE.getFreightCost();
        } else {
            return calculateFreightForMoreThanBaseProductAmount(productAmount);
        }
    }

    private Double calculateFreightForMoreThanBaseProductAmount(Integer productAmount) {
        double basePrice = FreightRate.BASE.getFreightCost();
        double additionalPartCount =
                (productAmount - FreightRate.BASE.getProductAmount()) /
                        Double.valueOf(FreightRate.ADDITIONAL.getProductAmount());
        double additionalPrice = Math.ceil(additionalPartCount) * FreightRate.ADDITIONAL.getFreightCost();
        return basePrice + additionalPrice;
    }

}
