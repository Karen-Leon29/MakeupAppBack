package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.service.ServiceCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api-cart")
public class ControllerCart {
    public static final Logger logger = LoggerFactory.getLogger(ControllerCart.class);

    @Autowired
    private ServiceCart serviceCart;

    @GetMapping("/listCart")
    public List<Cart> getCarts() {
        List<Cart> listCart = serviceCart.getCarts();
        logger.info("Registro lista de carritos");
        listCart.forEach(cart -> logger.info(cart.toString()));
        return listCart;
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return serviceCart.getCartById(id);
    }

    @PostMapping("/addCart")
    public Cart saveCart(@RequestBody Cart cart) {
        return serviceCart.saveCart(cart);
    }

    @DeleteMapping("/deleteCart/{id}")
    public void deleteCart(@PathVariable Long id) {
        serviceCart.deleteCart(id);
    }
}
