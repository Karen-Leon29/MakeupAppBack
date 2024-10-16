package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.domain.ProductImage;
import com.dorysoft.mackeupApp.repository.IRepositoryProductImage;
import com.dorysoft.mackeupApp.repository.IRepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProductImage implements IServiceProductImage {
    @Autowired
    private IRepositoryProductImage iRepositoryProductImage;

    @Autowired
    private IRepositoryProduct iRepositoryProduct;

    @Override
    public ProductImage addProductImage(Long productId, ProductImage productImage) {
        Product product = iRepositoryProduct.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        productImage.setProduct(product); // Establecer la relación con el producto
        return iRepositoryProductImage.save(productImage);
    }

    @Override
    public List<ProductImage> getImagesByProductId(Long productId) {
        return iRepositoryProductImage.findAllByProductId(productId);
    }

    @Override
    public void deleteProductImage(Long id) {
        iRepositoryProductImage.deleteById(id);
    }
}
