package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.CartProduct;
import com.dorysoft.mackeupApp.repository.IRepositoryCartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCartProduct implements IServiceCartProduct{
    @Autowired
    private IRepositoryCartProduct iRepositoryCartProduct;

    @Override
    public List<CartProduct> getCartProducts() {
        return iRepositoryCartProduct.findAll();
    }

    @Override
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return iRepositoryCartProduct.save(cartProduct);
    }

    @Override
    public CartProduct getCartProduct(Long id) {
        return iRepositoryCartProduct.findById(id).orElse(null);
    }

    @Override
    public CartProduct updateCartProduct(Long id, CartProduct cartProduct) {
        return iRepositoryCartProduct.findById(id)
                .map(existingCartProduct -> {
                    existingCartProduct.setCart(cartProduct.getCart());
                    existingCartProduct.setProduct(cartProduct.getProduct());
                    existingCartProduct.setAmount(cartProduct.getAmount());
                    return iRepositoryCartProduct.save(existingCartProduct);
                })
                .orElse(null);
    }

    @Override
    public void deleteCartProduct(Long id) {
        iRepositoryCartProduct.deleteById(id);
    }
}