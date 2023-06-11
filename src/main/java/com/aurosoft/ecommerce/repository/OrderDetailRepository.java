package com.aurosoft.ecommerce.repository;

import com.aurosoft.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
