package com.dementiev.webshop.dto;

import com.dementiev.webshop.enums.ProductType;
import lombok.Builder;

@Builder
public record OrderDto(
        Integer amount,
        Double price,
        ProductType type
) {
}
