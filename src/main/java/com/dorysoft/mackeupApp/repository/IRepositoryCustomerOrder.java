package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryCustomerOrder extends JpaRepository<CustomerOrder, Long> {
}
