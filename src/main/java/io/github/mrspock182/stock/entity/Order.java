package io.github.mrspock182.stock.entity;

import java.util.List;

public record Order(
        String orderId,
        List<Product> items
) {
}
