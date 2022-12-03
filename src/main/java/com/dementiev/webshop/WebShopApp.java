package com.dementiev.webshop;

import com.dementiev.webshop.entity.Order;
import com.dementiev.webshop.enums.ProductType;
import com.dementiev.webshop.exception.InvalidConsoleInputException;
import com.dementiev.webshop.service.FreightPriceCalculatorBookImpl;
import com.dementiev.webshop.service.OrderPriceCalculator;
import com.dementiev.webshop.service.PriceOutputFormatter;
import com.dementiev.webshop.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class WebShopApp {

    private final OrderPriceCalculator orderPriceCalculator;

    public static void main(String[] args) {

        BasicConfigurator.configure();

        WebShopApp webShopApp = new WebShopApp(
                new OrderPriceCalculator(
                        new FreightPriceCalculatorBookImpl(),
                        new PriceOutputFormatter()));
        webShopApp.run(args);
    }

    private void run(String[] args) {
        log.info("WebShopApp started!");
        log.info(String.format("args: %s", Arrays.toString(args)));
        if (args.length < 3) {
            throw new InvalidConsoleInputException("Not all input parameters are indicated");
        }
        Order order = createOrderFromInputParameters(args);
        log.info(orderPriceCalculator.calculate(order));
        log.info("WebShopApp finished!");
    }

    private Order createOrderFromInputParameters(String[] args) {
        return Order.builder()
                .amount(Utils.getIntegerFromString(args[0]))
                .price(Utils.getDoubleFromString(args[1]))
                .type(ProductType.getValue(args[2]))
                .build();
    }


}
