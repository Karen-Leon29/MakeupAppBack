package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.dto.CartDto;
import com.dorysoft.mackeupApp.service.IServiceCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/cart")
public class
ControllerCart {

    @Autowired
    private IServiceCart serviceCart;

    @PostMapping("/addProduct")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartDto cartDto) {
        try {
            Cart updatedCart = serviceCart.addProductToCart(cartDto);
            return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) {
        Cart cart = serviceCart.getCartByUser(userId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            serviceCart.removeProductFromCart(userId, productId);
            return new ResponseEntity<>("Product removed from cart successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error removing product from cart.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/clearCart/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        try {
            serviceCart.clearCart(userId);
            return new ResponseEntity<>("Cart cleared successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error clearing cart.", HttpStatus.BAD_REQUEST);
        }
    }
}