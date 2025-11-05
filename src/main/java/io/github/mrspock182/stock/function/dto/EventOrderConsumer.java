package io.github.mrspock182.stock.function.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EventOrderConsumer(
        String eventType,
        LocalDateTime timestamp,
        String orderId,
        List<ProductConsumer> items
) {
}