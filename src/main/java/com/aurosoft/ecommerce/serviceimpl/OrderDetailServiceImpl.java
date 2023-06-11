package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.OrderDetail;
import com.aurosoft.ecommerce.repository.OrderDetailRepository;
import com.aurosoft.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository repository;

    @Override
    public List<OrderDetail> listAllOrderDetails() {
        return repository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public OrderDetail insertOrderDetail(OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }

    @Override
    public int deleteOrderDetail(int id) {
        repository.deleteById(id);
        return id;
    }
}
