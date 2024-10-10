package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Cart;
import com.dorysoft.mackeupApp.domain.CartProduct;
import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.CartDto;
import com.dorysoft.mackeupApp.repository.IRepositoryCart;
import com.dorysoft.mackeupApp.repository.IRepositoryProduct;
import com.dorysoft.mackeupApp.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ServiceCart implements IServiceCart {
    @Autowired
    private IRepositoryCart iRepositoryCart;

    @Autowired
    private IRepositoryProduct iRepositoryProduct;

    @Autowired
    private IRepositoryUser iRepositoryUser;

    @Override
    public Cart addProductToCart(CartDto cartDto) {
        Optional<Product> productOptional = iRepositoryProduct.findById(cartDto.getProductId());
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Producto no encontrado para el ID proporcionado.");
        }

        Product product = productOptional.get();
        Cart cart = getCartByUser(cartDto.getUserId());
        CartProduct cartProduct = new CartProduct(null, cart, product, cartDto.getQuantity());
        cart.getProducts().add(cartProduct);
        updateCartTotal(cart);
        return iRepositoryCart.save(cart);
    }

    @Override
    public Cart getCartByUser(Long userId) {
        return iRepositoryCart.findByUserId(userId).orElseGet(() -> createNewCartForUser(userId));
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        cart.getProducts().removeIf(cartProduct -> cartProduct.getProduct().getId().equals(productId));
        updateCartTotal(cart);
        iRepositoryCart.save(cart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getCartByUser(userId);
        cart.getProducts().clear();
        cart.setTotalPrice(0.0);
        iRepositoryCart.save(cart);
    }

    private void updateCartTotal(Cart cart) {
        double totalPrice = cart.getProducts().stream()
                .mapToDouble(cartProduct -> cartProduct.getProduct().getPrice() * cartProduct.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
    }

    private Cart createNewCartForUser(Long userId) {
        Optional<User> userOptional = iRepositoryUser.findById(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado para el ID proporcionado.");
        }

        User user = userOptional.get();
        Cart newCart = new Cart(null, user, new ArrayList<>(), 0.0, true);
        return iRepositoryCart.save(newCart);
    }
}
