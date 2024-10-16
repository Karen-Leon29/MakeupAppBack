package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IRepositoryProductImage extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findAllByProductId(Long productId);
}
