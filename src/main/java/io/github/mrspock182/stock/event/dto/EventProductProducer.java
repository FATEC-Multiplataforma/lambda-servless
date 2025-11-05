package io.github.mrspock182.stock.event.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventProductProducer(
        String eventType,
        LocalDateTime timestamp,
        String id,
        String name,
        String description,
        BigDecimal price,
        String category,
        Boolean isActive,
        Integer qtyStock
) {
}