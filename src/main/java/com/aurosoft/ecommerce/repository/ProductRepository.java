package com.aurosoft.ecommerce.repository;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findByCategory(Category category);
    List<Product> findByBrand(Brand brand);
}

