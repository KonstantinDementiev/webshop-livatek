package com.dementiev.webshop.service;

import com.dementiev.webshop.dto.OrderDto;
import com.dementiev.webshop.enums.InputCustomParams;
import com.dementiev.webshop.service.entity.CountryService;
import com.dementiev.webshop.service.entity.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderWithCustomParametersPriceCalculator extends OrderPriceCalculator {

    private static final Double ONE_HUNDRED_PERCENT = 100.0;
    private final CurrencyService currencyService;
    private final CountryService countryService;

    public double calculate(OrderDto orderDto, Map<String, String> customParams) {
        double baseOrderPriceWithFreight = calculate(orderDto);

        double countryVatRate = getCountryVatRate(orderDto, customParams);
        double inputCurrencyRate = currencyService.getCurrencyRateByCode(
                customParams.get(InputCustomParams.INPUT_CURRENCY.getDescription()));
        double outputCurrencyRate = currencyService.getCurrencyRateByCode(
                customParams.get(InputCustomParams.OUTPUT_CURRENCY.getDescription()));

        return (baseOrderPriceWithFreight * ((ONE_HUNDRED_PERCENT + countryVatRate) / ONE_HUNDRED_PERCENT))
                * inputCurrencyRate / outputCurrencyRate;
    }

    private double getCountryVatRate(OrderDto orderDto, Map<String, String> customParams) {
        String countryVatValue = customParams.get(InputCustomParams.VAT.getDescription());
        if (!countryVatValue.isEmpty()) {
            return countryService.getVatRateByCodeAndType(countryVatValue, orderDto.type());
        }
        return 0.0;
    }
}
