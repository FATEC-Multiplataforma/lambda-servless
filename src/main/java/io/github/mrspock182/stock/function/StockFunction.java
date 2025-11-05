package io.github.mrspock182.stock.function;

import io.github.mrspock182.stock.entity.Order;
import io.github.mrspock182.stock.function.adapter.StockFunctionAdapter;
import io.github.mrspock182.stock.function.dto.EventOrderConsumer;
import io.github.mrspock182.stock.service.StockDeductionService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class StockFunction {
    private final StockDeductionService stockDeductionService;

    public StockFunction(StockDeductionService stockDeductionService) {
        this.stockDeductionService = stockDeductionService;
    }

    @Bean
    public Consumer<EventOrderConsumer> orderPaidConsumer() {
        return consumer -> {
            System.out.println("Chegou evento: " + consumer);
//            Order order = StockFunctionAdapter.cast(consumer);
//            stockDeductionService.deduction(order);
        };
    }
}