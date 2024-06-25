package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CustomerOrder;

import java.util.List;

public interface IServiceCustomerOrder {
    List<CustomerOrder> getCustomerOrders();
    CustomerOrder getCustomerOrderById(Long id);
    CustomerOrder saveCustomerOrder(CustomerOrder customerOrder);
    CustomerOrder updateCustomerOrder(Long id, CustomerOrder customerOrder);
    void deleteCustomerOrder(Long id);
}