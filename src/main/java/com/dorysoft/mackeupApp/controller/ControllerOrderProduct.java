package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.OrderProduct;
import com.dorysoft.mackeupApp.service.ServiceOrderProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-orderproduct")
public class ControllerOrderProduct {
    public static final Logger logger = LoggerFactory.getLogger(ControllerOrderProduct.class);

    @Autowired
    private ServiceOrderProduct serviceOrderProduct;

    @GetMapping("/listOrderProduct")
    public List<OrderProduct> getOrderProducts() {
        List<OrderProduct> listOrderProduct = this.serviceOrderProduct.getOrderProducts();
        logger.info("Obtained order product list");
        listOrderProduct.forEach(orderProduct -> logger.info(orderProduct.toString()));
        return listOrderProduct;
    }

    @GetMapping("/getOrderProduct/{id}")
    public OrderProduct getOrderProductById(@PathVariable Long id) {
        return serviceOrderProduct.getOrderProductById(id);
    }

    @PostMapping("/createOrderProduct")
    public OrderProduct createOrderProduct(@RequestBody OrderProduct orderProduct) {
        return serviceOrderProduct.saveOrderProduct(orderProduct);
    }

    @PutMapping("/updateOrderProduct")
    public OrderProduct updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        return serviceOrderProduct.updateOrderProduct(orderProduct);
    }

    @DeleteMapping("/deleteOrderProduct/{id}")
    public void deleteOrderProduct(@PathVariable Long id) {
        serviceOrderProduct.deleteOrderProduct(id);
    }
}