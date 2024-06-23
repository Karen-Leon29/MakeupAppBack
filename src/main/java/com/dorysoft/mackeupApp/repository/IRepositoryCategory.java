package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryCategory extends JpaRepository<Category, Long> {
}
