package com.dementiev.webshop;

import com.dementiev.webshop.dto.OrderDto;
import com.dementiev.webshop.enums.InputCustomParams;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.exception.InvalidConsoleInputException;
import com.dementiev.webshop.service.CustomParamsParser;
import com.dementiev.webshop.service.OrderWithCustomParametersPriceCalculator;
import com.dementiev.webshop.service.PriceOutputFormatter;
import com.dementiev.webshop.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStarter implements CommandLineRunner {

    private static final Integer BASE_ORDER_PARAMS_NUMBER = 3;
    private final CustomParamsParser customParamsParser;
    private final OrderWithCustomParametersPriceCalculator orderPriceCalculator;
    private final PriceOutputFormatter priceOutputFormatter;
    private String outputCurrencyForPrint = "DKK";

    @Override
    public void run(String[] args) {
        log.info(" -------------> WebShopApp started!");
        log.info(String.format("args: %s", Arrays.toString(args)));
        if (args.length < BASE_ORDER_PARAMS_NUMBER) {
            throw new InvalidConsoleInputException("Not all input parameters are indicated");
        }
        OrderDto orderDto = createOrderFromInputParameters(args);
        double orderPrice = calculateOrderPrice(args, orderDto);
        log.info(priceOutputFormatter.convertPriceToDecimalFormat(orderPrice) + " " + outputCurrencyForPrint);
        log.info(" -------------> WebShopApp finished!");
    }

    private double calculateOrderPrice(String[] args, OrderDto orderDto) {
        if (args.length > BASE_ORDER_PARAMS_NUMBER) {
            Map<String, String> customParams =
                    customParamsParser.getCustomParamsFromArgs(args, BASE_ORDER_PARAMS_NUMBER);
            outputCurrencyForPrint = customParams.get(InputCustomParams.OUTPUT_CURRENCY.getDescription());
            return orderPriceCalculator.calculate(orderDto, customParams);
        } else {
            return (orderPriceCalculator.calculate(orderDto));
        }
    }

    private OrderDto createOrderFromInputParameters(String[] args) {
        return OrderDto.builder()
                .amount(Utils.getIntegerFromString(args[0]))
                .price(Utils.getDoubleFromString(args[1]))
                .type(ProductType.getValue(args[2]))
                .build();
    }
}
