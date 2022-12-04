package com.dementiev.webshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private Integer id;
    private String code;
    private String area;
    private String type;
    private Double vat;
}
