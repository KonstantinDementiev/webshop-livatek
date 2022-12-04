package com.dementiev.webshop.service;

import com.dementiev.webshop.dto.OrderDto;
import com.dementiev.webshop.entity.Country;
import com.dementiev.webshop.entity.Currency;
import com.dementiev.webshop.enums.InputCustomParams;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.exception.InvalidInputProductAmountException;
import com.dementiev.webshop.exception.InvalidInputProductPriceException;
import com.dementiev.webshop.repository.CountryRepository;
import com.dementiev.webshop.repository.CurrencyRepository;
import com.dementiev.webshop.service.entity.CountryService;
import com.dementiev.webshop.service.entity.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {
        CurrencyService.class,
        CountryService.class,
        FreightPriceCalculator.class,
        OrderWithCustomParametersPriceCalculator.class
})
class OrderWithCustomParametersPriceCalculatorIT {

    @Autowired
    private OrderWithCustomParametersPriceCalculator orderCalculator;
    @MockBean
    private CurrencyRepository currencyRepository;
    @MockBean
    private CountryRepository countryRepository;

    @Test
    void calculate_case_1() {
        OrderDto orderDto = createOrderDto(5, null, ProductType.BOOK);

        InvalidInputProductPriceException thrown = assertThrows(
                InvalidInputProductPriceException.class,
                () -> orderCalculator.calculate(orderDto),
                "Expected orderCalculator.calculate(orderDto) to throw exception, but it didn't"
        );
        assertEquals("Input product price is NULL", thrown.getMessage());
    }

    @Test
    void calculate_case_2() {
        OrderDto orderDto = createOrderDto(5, -40.0, ProductType.BOOK);

        InvalidInputProductPriceException thrown = assertThrows(
                InvalidInputProductPriceException.class,
                () -> orderCalculator.calculate(orderDto),
                "Expected orderCalculator.calculate(orderDto) to throw exception, but it didn't"
        );
        assertEquals("Input product price can not be NEGATIVE", thrown.getMessage());
    }

    @Test
    void calculate_case_3() {
        OrderDto orderDto = createOrderDto(null, 10.0, ProductType.BOOK);

        InvalidInputProductAmountException thrown = assertThrows(
                InvalidInputProductAmountException.class,
                () -> orderCalculator.calculate(orderDto),
                "Expected orderCalculator.calculate(orderDto) to throw exception, but it didn't"
        );
        assertEquals("Input product amount is NULL", thrown.getMessage());
    }

    @Test
    void calculate_case_4() {
        OrderDto orderDto = createOrderDto(-5, 10.0, ProductType.BOOK);

        InvalidInputProductAmountException thrown = assertThrows(
                InvalidInputProductAmountException.class,
                () -> orderCalculator.calculate(orderDto),
                "Expected orderCalculator.calculate(orderDto) to throw exception, but it didn't"
        );
        assertEquals("Input product amount can not be NEGATIVE", thrown.getMessage());
    }

    @Test
    void calculate_case_5() {
        OrderDto orderDto = createOrderDto(0, 0.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(0, actualResult);
    }

    @Test
    void calculate_case_6() {
        OrderDto orderDto = createOrderDto(5, 0.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(50, actualResult);
    }

    @Test
    void calculate_case_7() {
        OrderDto orderDto = createOrderDto(0, 10.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(0, actualResult);
    }

    @Test
    void calculate_case_8() {
        OrderDto orderDto = createOrderDto(5, 10.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(100, actualResult);
    }

    @Test
    void calculate_case_9() {
        OrderDto orderDto = createOrderDto(10, 10.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(150, actualResult);
    }

    @Test
    void calculate_case_10() {
        OrderDto orderDto = createOrderDto(25, 10.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(350, actualResult);
    }

    @Test
    void calculate_case_11() {
        OrderDto orderDto = createOrderDto(25, 10.0, ProductType.ONLINE);
        Map<String, String> customParams = createCustomParams("", "", "");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(250, actualResult);
    }

    @Test
    void calculate_case_12() {
        OrderDto orderDto = createOrderDto(25, 10.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "", "GB");
        Currency currency = Currency.builder().code("DKK").rate(100.0).build();
        Country country = Country.builder().vat(20.0).build();

        when(currencyRepository.findByCode(anyString())).thenReturn(Optional.of(currency));
        when(countryRepository.findByCodeAndType(anyString(), anyString())).thenReturn(Optional.of(country));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(420, actualResult);
    }

    @Test
    void calculate_case_13() {
        OrderDto orderDto = createOrderDto(25, 200.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "GBP", "");
        Currency inputCurrency = Currency.builder().code("DKK").rate(100.0).build();
        Currency outputCurrency = Currency.builder().code("GBP").rate(871.07).build();

        when(currencyRepository.findByCode("DKK")).thenReturn(Optional.of(inputCurrency));
        when(currencyRepository.findByCode("GBP")).thenReturn(Optional.of(outputCurrency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(586, Math.ceil(actualResult));
    }

    @Test
    void calculate_case_14() {
        OrderDto orderDto = createOrderDto(25, 200.0, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("SEK", "", "");
        Currency inputCurrency = Currency.builder().code("SEK").rate(70.23).build();
        Currency outputCurrency = Currency.builder().code("DKK").rate(100.0).build();

        when(currencyRepository.findByCode("SEK")).thenReturn(Optional.of(inputCurrency));
        when(currencyRepository.findByCode("DKK")).thenReturn(Optional.of(outputCurrency));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(3582, Math.ceil(actualResult));
    }

    @Test
    void calculate_case_15() {
        OrderDto orderDto = createOrderDto(12, 139.95, ProductType.BOOK);
        Map<String, String> customParams = createCustomParams("", "SEK", "DK");
        Currency inputCurrency = Currency.builder().code("DKK").rate(100.0).build();
        Currency outputCurrency = Currency.builder().code("SEK").rate(70.23).build();
        Country country = Country.builder().vat(25.0).build();

        when(currencyRepository.findByCode("DKK")).thenReturn(Optional.of(inputCurrency));
        when(currencyRepository.findByCode("SEK")).thenReturn(Optional.of(outputCurrency));
        when(countryRepository.findByCodeAndType(anyString(), anyString())).thenReturn(Optional.of(country));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(3123, Math.ceil(actualResult));
    }

    @Test
    void calculate_case_16() {
        OrderDto orderDto = createOrderDto(12, 139.95, ProductType.ONLINE);
        Map<String, String> customParams = createCustomParams("EUR", "SEK", "DE");
        Currency inputCurrency = Currency.builder().code("EUR").rate(743.93).build();
        Currency outputCurrency = Currency.builder().code("SEK").rate(70.23).build();
        Country country = Country.builder().vat(19.0).build();

        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.of(inputCurrency));
        when(currencyRepository.findByCode("SEK")).thenReturn(Optional.of(outputCurrency));
        when(countryRepository.findByCodeAndType(anyString(), anyString())).thenReturn(Optional.of(country));

        double actualResult = orderCalculator.calculate(orderDto, customParams);
        assertEquals(21170, Math.ceil(actualResult));
    }

    private OrderDto createOrderDto(Integer amount, Double price, ProductType type) {
        return OrderDto.builder()
                .amount(amount)
                .price(price)
                .type(type)
                .build();
    }

    private Map<String, String> createCustomParams(String inputCurrency, String outputCurrency, String vat) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(InputCustomParams.INPUT_CURRENCY.getDescription(),
                inputCurrency.isEmpty() ? InputCustomParams.INPUT_CURRENCY.getDefaultValue() : inputCurrency);
        resultMap.put(InputCustomParams.OUTPUT_CURRENCY.getDescription(),
                outputCurrency.isEmpty() ? InputCustomParams.OUTPUT_CURRENCY.getDefaultValue() : outputCurrency);
        resultMap.put(InputCustomParams.VAT.getDescription(),
                vat.isEmpty() ? InputCustomParams.VAT.getDefaultValue() : vat);
        return resultMap;
    }

}