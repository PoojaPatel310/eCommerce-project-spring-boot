package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="orders-detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",referencedColumnName = "id",nullable = false)
    private OrderMaster orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    private Product product;
    @Column(name = "qty",nullable = false,length = 20)
    private int qty;
    @Column(name = "rate",nullable = false,length = 50)
    private Float rate;
    @Column(name = "amount",nullable = false,length = 50)
    private Float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderMaster getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderMaster orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public OrderDetail(int id, OrderMaster orderId, Product product, int qty, Float rate, Float amount) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.qty = qty;
        this.rate = rate;
        this.amount = amount;
    }

    public OrderDetail() {
    }
}
