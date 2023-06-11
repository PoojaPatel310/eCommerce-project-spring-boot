package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.Service;

import java.util.List;

public interface ServiceService {

    List<Service> listAllService();
    Service getServiceById(int id);
    Service insertService(Service service);
    Service updateService(Service service);
    int deleteService(int id);

}
