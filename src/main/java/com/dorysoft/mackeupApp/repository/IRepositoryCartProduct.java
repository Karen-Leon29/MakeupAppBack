package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryCartProduct extends JpaRepository<CartProduct, Long> {
}
