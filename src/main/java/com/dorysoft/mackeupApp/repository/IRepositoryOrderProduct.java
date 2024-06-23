package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryOrderProduct extends JpaRepository<OrderProduct, Long> {
}
