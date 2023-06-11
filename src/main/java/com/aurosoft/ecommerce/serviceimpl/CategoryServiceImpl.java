package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.repository.CategoryRepository;
import com.aurosoft.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository repository;
    @Override
    public List<Category> listAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Category insertCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public int deleteCategory(int id) {
        repository.deleteById(id);
        return id;
    }
}
