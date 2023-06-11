package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listAllCategories();
    Category getCategoryById(int id);
    Category insertCategory(Category category);
    Category updateCategory(Category category);
    int deleteCategory(int id);
}
