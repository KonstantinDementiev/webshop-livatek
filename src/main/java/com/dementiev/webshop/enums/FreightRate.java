package com.dementiev.webshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FreightRate {

    BASE(10, 50.0),
    ADDITIONAL(10, 25.0);

    private final Integer productAmount;
    private final Double freightCost;

}
