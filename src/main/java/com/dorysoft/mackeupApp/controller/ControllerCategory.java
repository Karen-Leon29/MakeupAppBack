package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Category;
import com.dorysoft.mackeupApp.service.ServiceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-category")
public class ControllerCategory {
    public static final Logger logger = LoggerFactory.getLogger(ControllerCategory.class);

    @Autowired
    private ServiceCategory serviceCategory;

    @GetMapping("/listCategory")
    public List<Category> getCategories(){
        List<Category> listCategory = this.serviceCategory.getCategories();
        logger.info("Registro lista de productos");
        listCategory.forEach(category -> logger.info(category.toString()));
        return listCategory;
    }

    @GetMapping("/getCategory/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return serviceCategory.getCategoryById(id);
    }

    @PostMapping("/createCategory")
    public Category createCategory(@RequestBody Category category) {
        return serviceCategory.saveCategory(category);
    }

    @PutMapping("/updateCategory/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return serviceCategory.updateCategory(id, category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable Long id) {
        serviceCategory.deleteCategory(id);
    }
}
