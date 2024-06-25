package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CustomerOrder;
import com.dorysoft.mackeupApp.repository.IRepositoryCustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomerOrder implements IServiceCustomerOrder {

    @Autowired
    private IRepositoryCustomerOrder iRepositoryCustomerOrder;

    @Override
    public List<CustomerOrder> getCustomerOrders() {
        return iRepositoryCustomerOrder.findAll();
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long id) {
        return iRepositoryCustomerOrder.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder saveCustomerOrder(CustomerOrder customerOrder) {
        return iRepositoryCustomerOrder.save(customerOrder);
    }

    @Override
    public CustomerOrder updateCustomerOrder(Long id, CustomerOrder customerOrder) {
        return iRepositoryCustomerOrder.findById(id)
                .map(existingCustomerOrder -> {
                    existingCustomerOrder.setUser(customerOrder.getUser());
                    existingCustomerOrder.setDateOrder(customerOrder.getDateOrder());
                    existingCustomerOrder.setStatus(customerOrder.getStatus());
                    existingCustomerOrder.setTotal(customerOrder.getTotal());
                    return iRepositoryCustomerOrder.save(existingCustomerOrder);
                })
                .orElse(null);
    }

    @Override
    public void deleteCustomerOrder(Long id) {
        iRepositoryCustomerOrder.deleteById(id);
    }
}