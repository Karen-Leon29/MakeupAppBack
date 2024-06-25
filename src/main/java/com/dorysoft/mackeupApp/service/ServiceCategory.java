package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Category;
import com.dorysoft.mackeupApp.repository.IRepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategory implements IServiceCategory{
    @Autowired
    private IRepositoryCategory iRepositoryCategory;

    @Override
    public List<Category> getCategories() {return this.iRepositoryCategory.findAll();}

    @Override
    public Category getCategoryById(Long id) {
        return iRepositoryCategory.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        return iRepositoryCategory.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return iRepositoryCategory.findById(id)
                .map(existingCategory -> {
                    existingCategory.setNameCategory(category.getNameCategory());
                    existingCategory.setDescriptionCategory(category.getDescriptionCategory());
                    return iRepositoryCategory.save(existingCategory);
                })
                .orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
        iRepositoryCategory.deleteById(id);
    }
}