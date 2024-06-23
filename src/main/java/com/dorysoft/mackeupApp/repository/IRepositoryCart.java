package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryCart extends JpaRepository<Cart, Long> {

}
