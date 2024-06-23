package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.OrderProduct;
import com.dorysoft.mackeupApp.repository.IRepositoryOrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOrderProduct implements IServiceOrderProduct {

    @Autowired
    private IRepositoryOrderProduct iRepositoryOrderProduct;

    @Override
    public List<OrderProduct> getOrderProducts() {
        return iRepositoryOrderProduct.findAll();
    }

    @Override
    public OrderProduct getOrderProductById(Long id) {
        return iRepositoryOrderProduct.findById(id).orElse(null);
    }

    @Override
    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return iRepositoryOrderProduct.save(orderProduct);
    }

    @Override
    public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
        if (iRepositoryOrderProduct.existsById(orderProduct.getId())) {
            return iRepositoryOrderProduct.save(orderProduct);
        }
        return null;
    }

    @Override
    public void deleteOrderProduct(Long id) {
        iRepositoryOrderProduct.deleteById(id);
    }
}
