package io.github.mrspock182.stock.event.adapter;

import io.github.mrspock182.stock.event.dto.EventProductProducer;
import io.github.mrspock182.stock.repository.orm.ProductOrm;

import java.time.LocalDateTime;

public class ProductEventAdapter {
    private ProductEventAdapter() {
    }

    public static EventProductProducer cast(ProductOrm orm) {
        return new EventProductProducer(
                "StockProductUpdate",
                LocalDateTime.now(),
                orm.getId(),
                orm.getName(),
                orm.getDescription(),
                orm.getPrice(),
                orm.getCategory(),
                orm.getActive(),
                orm.getQtyStock());
    }
}
