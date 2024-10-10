package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.dto.CartDto;

public interface IServiceCart {
    Cart addProductToCart(CartDto cartDto);
    Cart getCartByUser(Long userId);
    void removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}