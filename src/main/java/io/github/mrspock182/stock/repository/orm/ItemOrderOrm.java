package io.github.mrspock182.stock.repository.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "item_order")
@Table(name = "item_order")
public class ItemOrderOrm {
    @EmbeddedId
    private ItemOrderId id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrdersOrm order;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductOrm product;
    private Integer qty;
    private BigDecimal price;

    public ItemOrderId getId() {
        return id;
    }

    public void setId(ItemOrderId id) {
        this.id = id;
    }

    public OrdersOrm getOrder() {
        return order;
    }

    public void setOrder(OrdersOrm order) {
        this.order = order;
    }

    public ProductOrm getProduct() {
        return product;
    }

    public void setProduct(ProductOrm product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}