package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.CartProduct;
import com.dorysoft.mackeupApp.service.ServiceCartProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-cartproduct")
public class ControllerCartProduct {
    public static final Logger logger = LoggerFactory.getLogger(ControllerCartProduct.class);

    @Autowired
    private ServiceCartProduct serviceCartProduct;

    @GetMapping("/listCartProduct")
    public List<CartProduct> getCartProducts() {
        List<CartProduct> listCartProduct = this.serviceCartProduct.getCartProducts();
        logger.info("Obtained cart product list");
        listCartProduct.forEach(cartProduct -> logger.info(cartProduct.toString()));
        return listCartProduct;
    }

    @GetMapping("/getCartProduct/{id}")
    public CartProduct getCartProductById(@PathVariable Long id) {
        return serviceCartProduct.getCartProductById(id);
    }

    @PostMapping("/createCartProduct")
    public CartProduct createCartProduct(@RequestBody CartProduct cartProduct) {
        return serviceCartProduct.saveCartProduct(cartProduct);
    }

    @PutMapping("/updateCartProduct")
    public CartProduct updateCartProduct(@RequestBody CartProduct cartProduct) {
        return serviceCartProduct.updateCartProduct(cartProduct);
    }

    @DeleteMapping("/deleteCartProduct/{id}")
    public void deleteCartProduct(@PathVariable Long id) {
        serviceCartProduct.deleteCartProduct(id);
    }
}