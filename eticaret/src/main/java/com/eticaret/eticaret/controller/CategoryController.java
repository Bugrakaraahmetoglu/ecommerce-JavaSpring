package com.eticaret.eticaret.controller;

import com.eticaret.eticaret.model.Category;
import com.eticaret.eticaret.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        try{
            Category saveCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(saveCategory);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllCategories")
    public List<Category> getALlCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/search")
    public List<Category> searchCategories(@RequestParam("name") String name) {
        return categoryService.findByNameContaining(name);
    }
}
