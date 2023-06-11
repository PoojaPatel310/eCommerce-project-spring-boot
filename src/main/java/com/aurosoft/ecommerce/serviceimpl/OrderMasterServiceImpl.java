package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.OrderMaster;
import com.aurosoft.ecommerce.repository.OrderMasterRepository;
import com.aurosoft.ecommerce.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    OrderMasterRepository repository;
    @Override
    public List<OrderMaster> listAllOrderMasters() {
        return repository.findAll();
    }

    @Override
    public OrderMaster getOrderMasterById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public OrderMaster insertOrderMaster(OrderMaster orderMaster) {
        return repository.save(orderMaster);
    }

    @Override
    public OrderMaster updateOrderMaster(OrderMaster orderMaster) {
        return repository.save(orderMaster);
    }

    @Override
    public int deleteOrderMaster(int id) {
        repository.deleteById(id);
        return id;
    }
}
