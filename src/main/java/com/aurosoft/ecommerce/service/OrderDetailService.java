package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> listAllOrderDetails();
    OrderDetail getOrderDetailById(int id);
    OrderDetail insertOrderDetail(OrderDetail orderDetail);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    int deleteOrderDetail(int id);
}
