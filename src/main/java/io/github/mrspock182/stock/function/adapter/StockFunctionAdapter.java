package io.github.mrspock182.stock.function.adapter;

import io.github.mrspock182.stock.entity.Order;
import io.github.mrspock182.stock.entity.Product;
import io.github.mrspock182.stock.function.dto.EventOrderConsumer;

public class StockFunctionAdapter {
    private StockFunctionAdapter() {
    }

    public static Order cast(EventOrderConsumer producer) {
        return new Order(
                producer.orderId(),
                producer.items()
                        .stream()
                        .map(v -> new Product(v.id(), v.qty()))
                        .toList());
    }

}
