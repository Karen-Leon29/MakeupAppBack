package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.service.ServiceProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return listProduct;
    }
    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable Long id) {
        return serviceProduct.getProductById(id);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product) {
        return serviceProduct.saveProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return serviceProduct.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        serviceProduct.deleteProduct(id);
    }
}
