package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();

    List<Product> getProductByCategory(Category category);
    List<Product> getProductByBrand(Brand brand);
    Product getProductById(int id);
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    int deleteProduct(int id);
}
