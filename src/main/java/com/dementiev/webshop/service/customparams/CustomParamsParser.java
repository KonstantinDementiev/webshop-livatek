package com.dementiev.webshop.service.customparams;

import java.util.Map;

public interface CustomParamsParser {

    Map<String, String> getCustomParamsFromArgs(String[] args, Integer baseOrderParamsNumber);
}
