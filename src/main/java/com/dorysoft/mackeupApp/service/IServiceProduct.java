package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Product;

import java.util.List;

public interface IServiceProduct {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
