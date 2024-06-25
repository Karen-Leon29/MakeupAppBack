package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;

import java.util.List;

public interface IServiceCart {
    List<Cart> getCarts();
    Cart getCartById(Long id);
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}
