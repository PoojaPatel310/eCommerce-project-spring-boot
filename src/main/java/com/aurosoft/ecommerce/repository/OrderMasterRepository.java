package com.aurosoft.ecommerce.repository;

import com.aurosoft.ecommerce.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {
}
