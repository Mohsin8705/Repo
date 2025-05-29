package com.sm.backend.controller;

import com.sm.backend.Request.ProductRequest;
import com.sm.backend.Service.ProductService;
import com.sm.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }
    @GetMapping("/find")
    public List<Product> findByNameAndPrice (@RequestParam (required = false) String name ,
                                             @RequestParam(required = false) Double price){
        return productService.findByNameAndPrice(name, price);
    }
    @GetMapping("/name")
    public List<Product> findByName (@RequestParam (required = false) String name){
        return productService.findByName(name);
    }
    @GetMapping("/price")
    public List<Product> findByPrice (@RequestParam (required = false) Double price){
        return productService.findByPrice(price);
    }
    @PostMapping
    public void save (@RequestBody ProductRequest product){
        productService.addNew(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return productService.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Long id){
        return productService.deleteById(id);
    }
}
