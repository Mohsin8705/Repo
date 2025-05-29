package com.sm.backend.controller;

import com.sm.backend.Request.CategoryRequest;
import com.sm.backend.Service.CategoryService;
import com.sm.backend.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> finddAll(){
        return categoryService.findAll();
    }
    @PostMapping
    public void save (@RequestBody CategoryRequest category){
         categoryService.addNew(category);
    }
    @GetMapping("/{id}")
    public Category findById (@PathVariable Long id){
        return categoryService.findById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        return categoryService.update(id, categoryRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Long id){
        return categoryService.deleteById(id);
    }
}
