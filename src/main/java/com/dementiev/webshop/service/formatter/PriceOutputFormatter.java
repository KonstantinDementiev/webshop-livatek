package com.dementiev.webshop.service.formatter;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class PriceOutputFormatter {

    private final static String ORDER_PRICE_FORMAT = "##.00";
    private final static String ZERO_PRICE_VALUE = "0";
    private final static Character ORDER_PRICE_DECIMAL_SEPARATOR = '.';

    public String convertPriceToDecimalFormat(double price) {
        if (price == 0.0) {
            return ZERO_PRICE_VALUE;
        }
        return getPriceFormat().format(price);
    }

    private DecimalFormat getPriceFormat() {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(currentLocale);
        decimalFormatSymbols.setDecimalSeparator(ORDER_PRICE_DECIMAL_SEPARATOR);
        return new DecimalFormat(ORDER_PRICE_FORMAT, decimalFormatSymbols);
    }
}
