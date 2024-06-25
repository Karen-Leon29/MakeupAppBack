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
    public Product updateProduct(Long id, Product product) {
        return iRepositoryProduct.findById(id)
                .map(existingProduct -> {
                    existingProduct.setNameProduct(product.getNameProduct());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setAmount(product.getAmount());
                    existingProduct.setCodeProduct(product.getCodeProduct());
                    existingProduct.setPhotoProduct(product.getPhotoProduct());
                    existingProduct.setCategory(product.getCategory());
                    return iRepositoryProduct.save(existingProduct);
                })
                .orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        iRepositoryProduct.deleteById(id);
    }
}