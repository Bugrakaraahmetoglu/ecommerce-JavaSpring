package com.eticaret.eticaret.controller;

import com.eticaret.eticaret.model.Product;
import com.eticaret.eticaret.repsonse.ProductDTO;
import com.eticaret.eticaret.request.ProductRequest;
import com.eticaret.eticaret.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());

        // Kategoriyi ID ile kaydet
        Product savedProduct = productService.saveProduct(product, productRequest.getCategoryId());

        if (savedProduct != null) {
            return ResponseEntity.ok(savedProduct);
        } else {
            // Kategori bulunamadığında farklı bir yanıt döndür
            return ResponseEntity.badRequest().body("Kategori bulunamadı."); // Hata mesajı döndür
        }
    }

    @GetMapping("/getAllProducts")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}
