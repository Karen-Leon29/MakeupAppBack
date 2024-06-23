package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.repository.IRepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct implements IServiceProduct{

    @Autowired
    private IRepositoryProduct iRepositoryProduct;

    @Override
    public List<Product> getProducts() {
        return this.iRepositoryProduct.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return iRepositoryProduct.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return iRepositoryProduct.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if (iRepositoryProduct.existsById(product.getId())) {
            return iRepositoryProduct.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        iRepositoryProduct.deleteById(id);
    }
}
