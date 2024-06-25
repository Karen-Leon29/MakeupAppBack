package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CartProduct;

import java.util.List;

public interface IServiceCartProduct {
    List<CartProduct> getCartProducts();
    CartProduct saveCartProduct(CartProduct cartProduct);
    CartProduct getCartProduct(Long id);
    CartProduct updateCartProduct(Long id, CartProduct cartProduct);
    void deleteCartProduct(Long id);
}