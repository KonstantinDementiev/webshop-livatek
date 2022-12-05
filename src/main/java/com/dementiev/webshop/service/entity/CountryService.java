package com.dementiev.webshop.service.entity;

import com.dementiev.webshop.enums.ProductType;

public interface CountryService {

    double getVatRateByCodeAndType(String code, ProductType type);
}
