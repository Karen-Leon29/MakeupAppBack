package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Category;

import java.util.List;

public interface IServiceCategory {
    List<Category> getCategories();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
