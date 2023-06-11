package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.Service;
import com.aurosoft.ecommerce.repository.ServiceRepository;
import com.aurosoft.ecommerce.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository repository;
    @Override
    public List<Service> listAllService() {
        return repository.findAll();
    }

    @Override
    public Service getServiceById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Service insertService(Service service) {
        return repository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        return repository.save(service);
    }

    @Override
    public int deleteService(int id) {
        repository.deleteById(id);
        return id;
    }
}
