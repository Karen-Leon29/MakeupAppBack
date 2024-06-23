package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.CustomerOrder;
import com.dorysoft.mackeupApp.service.ServiceCustomerOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-customerorder")
public class ControllerCustomerOrder {
    public static final Logger logger = LoggerFactory.getLogger(ControllerCustomerOrder.class);

    @Autowired
    private ServiceCustomerOrder serviceCustomerOrder;

    @GetMapping("/listCustomerOrder")
    public List<CustomerOrder> getCustomerOrders() {
        List<CustomerOrder> listCustomerOrder = this.serviceCustomerOrder.getCustomerOrders();
        logger.info("Obtained customer order list");
        listCustomerOrder.forEach(customerOrder -> logger.info(customerOrder.toString()));
        return listCustomerOrder;
    }

    @GetMapping("/getCustomerOrder/{id}")
    public CustomerOrder getCustomerOrderById(@PathVariable Long id) {
        return serviceCustomerOrder.getCustomerOrderById(id);
    }

    @PostMapping("/createCustomerOrder")
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return serviceCustomerOrder.saveCustomerOrder(customerOrder);
    }

    @PutMapping("/updateCustomerOrder")
    public CustomerOrder updateCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return serviceCustomerOrder.updateCustomerOrder(customerOrder);
    }

    @DeleteMapping("/deleteCustomerOrder/{id}")
    public void deleteCustomerOrder(@PathVariable Long id) {
        serviceCustomerOrder.deleteCustomerOrder(id);
    }
}