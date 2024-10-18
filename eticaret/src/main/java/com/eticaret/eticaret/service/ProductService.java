package com.eticaret.eticaret.service;

import com.eticaret.eticaret.model.Category;
import com.eticaret.eticaret.model.Product;
import com.eticaret.eticaret.repository.ProductRepository;
import com.eticaret.eticaret.repsonse.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService; // Kategori servisini ekliyoruz

    public Product saveProduct(Product product, Integer categoryId) {
        Optional<Category> categoryOptional = categoryService.findById(categoryId);

        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
            return productRepository.save(product);
        } else {
            // Kategori bulunamadığında farklı bir yanıt döndür
            return null; // Burada null veya başka bir değer döndürebilirsiniz
        }
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.getCategory().getName() // Kategori adını al
                ))
                .collect(Collectors.toList());
    }
}
