package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.ProductImage;
import com.dorysoft.mackeupApp.service.ServiceProductImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api-product-image")
public class ControllerProductImage {

    @Autowired
    private ServiceProductImage serviceProductImage;

    // Endpoint para agregar una imagen a un producto
    @PostMapping("/add/{productId}")
    public ProductImage addProductImage(@PathVariable Long productId, @RequestBody ProductImage productImage) {
        return serviceProductImage.addProductImage(productId, productImage);
    }

    // Endpoint para obtener todas las imágenes de un producto
    @GetMapping("/list/{productId}")
    public List<ProductImage> getProductImages(@PathVariable Long productId) {
        return serviceProductImage.getImagesByProductId(productId);
    }

    // Endpoint para eliminar una imagen de un producto
    @DeleteMapping("/deleteImageProduct/{id}")
    public void deleteProductImage(@PathVariable Long id) {
        serviceProductImage.deleteProductImage(id);
    }
}
