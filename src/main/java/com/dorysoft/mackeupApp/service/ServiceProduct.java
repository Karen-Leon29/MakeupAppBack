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
}
