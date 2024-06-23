package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.repository.IRepositoryCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCart implements IServiceCart{
    @Autowired
    private IRepositoryCart repositoryCart;

    @Override
    public List<Cart> getCarts() {
        return repositoryCart.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return repositoryCart.findById(id).orElse(null);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return repositoryCart.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        repositoryCart.deleteById(id);
    }
}
