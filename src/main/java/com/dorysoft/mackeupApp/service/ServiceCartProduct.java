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
    public CartProduct getCartProductById(Long id) {
        return iRepositoryCartProduct.findById(id).orElse(null);
    }

    @Override
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return iRepositoryCartProduct.save(cartProduct);
    }

    @Override
    public CartProduct updateCartProduct(CartProduct cartProduct) {
        if (iRepositoryCartProduct.existsById(cartProduct.getId())) {
            return iRepositoryCartProduct.save(cartProduct);
        }
        return null;
    }

    @Override
    public void deleteCartProduct(Long id) {
        iRepositoryCartProduct.deleteById(id);
    }
}