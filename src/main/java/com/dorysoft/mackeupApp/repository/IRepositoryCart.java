package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IRepositoryCart extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}