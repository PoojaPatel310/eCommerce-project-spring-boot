package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.repository.ProductRepository;
import com.aurosoft.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> getProductByBrand(Brand brand) {
        return repository.findByBrand(brand);
    }


    @Override
    public Product getProductById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product insertProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public int deleteProduct(int id) {
        repository.deleteById(id);
        return id;
    }
}
