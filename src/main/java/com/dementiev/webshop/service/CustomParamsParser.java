package com.dementiev.webshop.service;

import com.dementiev.webshop.enums.InputCustomParams;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomParamsParser {

    private static final String KEY_VALUE_DELIMITER = "=";

    public Map<String, String> getCustomParamsFromArgs(String[] args, Integer baseOrderParamsNumber) {
        Map<String, String> customParamsMap = createCustomParamsMap();
        if (Objects.isNull(args) || Objects.isNull(baseOrderParamsNumber) || args.length < baseOrderParamsNumber){
            return customParamsMap;
        }
        for (int i = baseOrderParamsNumber; i < args.length; i++) {
            for (String key : customParamsMap.keySet()) {
                if (args[i].contains(key)) {
                    customParamsMap.put(key, args[i].substring(args[i].indexOf(KEY_VALUE_DELIMITER) + 1));
                }
            }
        }
        return customParamsMap;
    }

    private Map<String, String> createCustomParamsMap() {
        Map<String, String> result = new HashMap<>();
        Arrays.stream(InputCustomParams.values())
                .forEach(param -> result.put(param.getDescription(), param.getDefaultValue()));
        return result;
    }
}
