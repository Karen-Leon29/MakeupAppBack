package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;

import java.util.List;

public interface IServiceCart {
    List<Cart> getCarts();
    Cart getCartById(Long id);
    Cart saveCart(Cart cart);
    void deleteCart(Long id);
}
