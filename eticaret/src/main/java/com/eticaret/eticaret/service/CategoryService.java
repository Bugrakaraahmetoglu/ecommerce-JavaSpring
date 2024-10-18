package com.eticaret.eticaret.service;

import com.eticaret.eticaret.model.Category;
import com.eticaret.eticaret.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Category> findByNameContaining(String name){
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Category> findById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
