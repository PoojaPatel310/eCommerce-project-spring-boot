package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name="orders-master")
public class OrderMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "order_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @Column(name = "order_amount",nullable = false,length = 50)
    private Float orderAmount;

    @OneToMany(mappedBy = "orderId",fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetailList = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Set<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(Set<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public OrderMaster(int id, Date orderDate, User user, Float orderAmount, Set<OrderDetail> orderDetailList) {
        this.id = id;
        this.orderDate = orderDate;
        this.user = user;
        this.orderAmount = orderAmount;
        this.orderDetailList = orderDetailList;
    }

    public OrderMaster() {
    }
}


