package com.dementiev.webshop.service;

import com.dementiev.webshop.enums.InputCustomParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {CustomParamsParser.class})
class CustomParamsParserTest {

    @Autowired
    private CustomParamsParser parser;

    @Test
    void getCustomParamsFromArgs_when_inputStringArrayIsNull_then_returnDefaultMap() {
        Map<String, String> expectedResult = createDefaultMap();
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(null, 3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCustomParamsFromArgs_when_inputParamsNumberIsNull_then_returnDefaultMap() {
        Map<String, String> expectedResult = createDefaultMap();
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(new String[]{}, null);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCustomParamsFromArgs_when_inputArrayLengthLessThanParamsNumber_then_returnDefaultMap() {
        Map<String, String> expectedResult = createDefaultMap();
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(new String[]{"1", "2"}, 3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCustomParamsFromArgs_when_inputArrayLengthGreaterThanParamsNumber_then_returnNotDefaultMap_case_1() {
        Map<String, String> expectedResult = createDefaultMap();
        expectedResult.put(InputCustomParams.INPUT_CURRENCY.getDescription(), "4");
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(
                new String[]{"1", "2", "3", "--input-currency=4"}, 3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCustomParamsFromArgs_when_inputArrayLengthGreaterThanParamsNumber_then_returnNotDefaultMap_case_2() {
        Map<String, String> expectedResult = createDefaultMap();
        expectedResult.put(InputCustomParams.INPUT_CURRENCY.getDescription(), "4");
        expectedResult.put(InputCustomParams.VAT.getDescription(), "5");
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(
                new String[]{"1", "2", "3", "--input-currency=4", "--vat=5"}, 3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCustomParamsFromArgs_when_inputArrayLengthGreaterThanParamsNumber_then_returnNotDefaultMap_case_3() {
        Map<String, String> expectedResult = createDefaultMap();
        expectedResult.put(InputCustomParams.INPUT_CURRENCY.getDescription(), "4");
        expectedResult.put(InputCustomParams.OUTPUT_CURRENCY.getDescription(), "6");
        expectedResult.put(InputCustomParams.VAT.getDescription(), "5");
        Map<String, String> actualResult = parser.getCustomParamsFromArgs(
                new String[]{"1", "2", "3", "--vat=5", "--input-currency=4", "--output-currency=6"}, 3);
        assertEquals(expectedResult, actualResult);
    }

    private Map<String, String> createDefaultMap() {
        Map<String, String> result = new HashMap<>();
        Arrays.stream(InputCustomParams.values())
                .forEach(param -> result.put(param.getDescription(), param.getDefaultValue()));
        return result;
    }
}