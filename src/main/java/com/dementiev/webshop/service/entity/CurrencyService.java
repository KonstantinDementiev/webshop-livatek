package com.dementiev.webshop.service.entity;

import com.dementiev.webshop.entity.Currency;
import com.dementiev.webshop.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;

    public double getCurrencyRateByCode(String inputCurrencyCode) {
        Optional<Currency> optionalCurrency = repository.findByCode(inputCurrencyCode);
        if (optionalCurrency.isPresent()) {
            return optionalCurrency.get().getRate();
        }
        return 0.0;
    }
}
