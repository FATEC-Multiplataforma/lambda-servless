package io.github.mrspock182.stock.service;

import io.github.mrspock182.stock.entity.Order;
import io.github.mrspock182.stock.entity.Product;
import io.github.mrspock182.stock.entity.enumerable.OrderStatus;
import io.github.mrspock182.stock.event.ProductEvent;
import io.github.mrspock182.stock.exception.NotFoundException;
import io.github.mrspock182.stock.repository.OrderRepositoryWithMySQL;
import io.github.mrspock182.stock.repository.ProductRepositoryWithMySQL;
import io.github.mrspock182.stock.repository.orm.OrdersOrm;
import io.github.mrspock182.stock.repository.orm.ProductOrm;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockDeductionService {
    private static final Logger LOG = LoggerFactory.getLogger(StockDeductionService.class);

    private final ProductEvent productEvent;
    private final OrderRepositoryWithMySQL orderRepository;
    private final ProductRepositoryWithMySQL productRepository;

    public StockDeductionService(
            ProductEvent productEvent,
            OrderRepositoryWithMySQL orderRepository,
            ProductRepositoryWithMySQL productRepository) {
        this.productEvent = productEvent;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public void deduction(Order order) {
        OrdersOrm orders = orderRepository.findByIdForUpdate(order.orderId())
                .orElseThrow(() -> new NotFoundException("Order not found: " + order.orderId()));

        for (Product item : order.items()) {
            ProductOrm product = productRepository.findByIdForUpdate(item.id())
                    .orElse(null);

            if (product == null || product.getQtyStock() < item.qty()) {
                cancelarPedido(orders);
                return;
            }

            atualizarEstoque(product, item.qty());
        }

        concluirPedido(orders);
    }

    private void cancelarPedido(OrdersOrm order) {
        order.setStatus(OrderStatus.CANCELADO);
        order.setUpdateAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    private void concluirPedido(OrdersOrm order) {
        order.setStatus(OrderStatus.CONCLUIDO);
        order.setUpdateAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    private void atualizarEstoque(ProductOrm product, int qty) {
        product.setQtyStock(product.getQtyStock() - qty);
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);
        productEvent.send(product);
    }

}
