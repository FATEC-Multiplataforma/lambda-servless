package io.github.mrspock182.stock.function;

import io.github.mrspock182.stock.function.dto.StockMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class StockFunction {

    @Bean
    public Consumer<StockMessage> orderPaidConsumer() {
        return consumer -> {
            System.out.println("Chegou evento: " + consumer.name());
//            Order order = StockFunctionAdapter.cast(consumer);
//            stockDeductionService.deduction(order);
        };
    }
}