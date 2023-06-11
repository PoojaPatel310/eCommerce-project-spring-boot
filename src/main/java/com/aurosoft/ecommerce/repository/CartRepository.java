package com.aurosoft.ecommerce.repository;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findByUser(User user);
}
