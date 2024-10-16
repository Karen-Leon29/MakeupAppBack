package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.ProductImage;

import java.util.List;

public interface IServiceProductImage {
    ProductImage addProductImage(Long productId, ProductImage productImage);
    List<ProductImage> getImagesByProductId(Long productId);
    void deleteProductImage(Long id);
}
