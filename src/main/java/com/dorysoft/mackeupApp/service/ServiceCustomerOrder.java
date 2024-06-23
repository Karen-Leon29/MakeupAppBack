package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CustomerOrder;
import com.dorysoft.mackeupApp.repository.IRepositoryCustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomerOrder implements IServiceCustomerOrder {

    @Autowired
    private IRepositoryCustomerOrder repositoryCustomerOrder;

    @Override
    public List<CustomerOrder> getCustomerOrders() {
        return repositoryCustomerOrder.findAll();
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long id) {
        return repositoryCustomerOrder.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder saveCustomerOrder(CustomerOrder customerOrder) {
        return repositoryCustomerOrder.save(customerOrder);
    }

    @Override
    public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
        if (repositoryCustomerOrder.existsById(customerOrder.getId())) {
            return repositoryCustomerOrder.save(customerOrder);
        }
        return null;
    }

    @Override
    public void deleteCustomerOrder(Long id) {
        repositoryCustomerOrder.deleteById(id);
    }
}