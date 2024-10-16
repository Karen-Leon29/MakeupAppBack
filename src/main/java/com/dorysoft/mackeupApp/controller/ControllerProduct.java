package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.Product;
import com.dorysoft.mackeupApp.domain.ProductImage;
import com.dorysoft.mackeupApp.service.ServiceProduct;
import com.dorysoft.mackeupApp.service.ServiceProductImage; // Asegúrate de importar el servicio correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api-product")
public class ControllerProduct {
    @Autowired
    private ServiceProduct serviceProduct;

    @Autowired
    private ServiceProductImage serviceProductImage; // Importa el servicio para las imágenes

    @GetMapping("/listProduct")
    public List<Product> getProducts(){
        return this.serviceProduct.getProducts();
    }

    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable Long id) {
        return serviceProduct.getProductById(id);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product) {
        return serviceProduct.saveProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return serviceProduct.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        serviceProduct.deleteProduct(id);
    }

    // Método para agregar imágenes a un producto
    @PostMapping("/addImage/{productId}")
    public ProductImage addImageToProduct(@PathVariable Long productId, @RequestBody ProductImage productImage) {
        return serviceProductImage.addProductImage(productId, productImage);
    }

    // Método para obtener las imágenes de un producto
    @GetMapping("/getImages/{productId}")
    public List<ProductImage> getProductImages(@PathVariable Long productId) {
        return serviceProductImage.getImagesByProductId(productId);
    }
}