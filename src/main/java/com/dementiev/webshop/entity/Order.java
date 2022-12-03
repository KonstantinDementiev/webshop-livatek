package com.dementiev.webshop.entity;

import com.dementiev.webshop.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private Integer amount;
    private Double price;
    private ProductType type;

}
