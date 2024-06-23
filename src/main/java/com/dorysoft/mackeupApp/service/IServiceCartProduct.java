package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CartProduct;

import java.util.List;

public interface IServiceCartProduct {
    List<CartProduct> getCartProducts();
    CartProduct getCartProductById(Long id);
    CartProduct saveCartProduct(CartProduct cartProduct);
    CartProduct updateCartProduct(CartProduct cartProduct);
    void deleteCartProduct(Long id);
}