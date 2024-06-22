package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryProduct extends JpaRepository<Product, Long> {
}
