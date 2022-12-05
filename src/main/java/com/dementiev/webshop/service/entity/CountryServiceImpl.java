package com.dementiev.webshop.service.entity;

import com.dementiev.webshop.entity.Country;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.repository.CountryRepository;
import com.dementiev.webshop.service.entity.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    public double getVatRateByCodeAndType(String code, ProductType type) {
        Optional<Country> countryOptional = repository.findByCodeAndType(code, type.getDescription());
        if (countryOptional.isEmpty()) {
            countryOptional = repository.findByCodeAndType(code, ProductType.ALL.getDescription());
        }
        if (countryOptional.isPresent()) {
            return countryOptional.get().getVat();
        }
        return 0.0;
    }
}
