package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.repository.IRepositoryCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCart implements IServiceCart{
    @Autowired
    private IRepositoryCart iRepositoryCart;

    @Override
    public List<Cart> getCarts() {
        return iRepositoryCart.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return iRepositoryCart.findById(id).orElse(null);
    }

    public Cart updateCart(Long id, Cart cart) {
        return iRepositoryCart.findById(id)
                .map(existingCart ->{
                    existingCart.setUser(cart.getUser());
                    existingCart.setStatus(cart.getStatus());
                    return iRepositoryCart.save(existingCart);
                })
                .orElse(null);
    }

    @Override
    public void deleteCart(Long id) {
        iRepositoryCart.deleteById(id);
    }
}
