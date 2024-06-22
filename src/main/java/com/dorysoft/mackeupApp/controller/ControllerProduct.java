package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.service.ServiceProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-product")
public class ControllerProduct {
    public static final Logger logger = LoggerFactory.getLogger(ControllerProduct.class);

    @Autowired
    private ServiceProduct serviceProduct;

    @GetMapping("/listProduct")
    public List<Product> getProducts(){
        List<Product> listProduct = this.serviceProduct.getProducts();
        logger.info("Registro lista de productos");
        listProduct.forEach(product -> logger.info(product.toString()));
        System.out.println("Hola mundo");
        return listProduct;
    }
}
