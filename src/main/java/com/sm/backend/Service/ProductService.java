package com.sm.backend.Service;

import com.sm.backend.Repository.CategoryRepository;
import com.sm.backend.Repository.ProductRepository;
import com.sm.backend.Request.ProductRequest;
import com.sm.backend.model.Category;
import com.sm.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository theProductRepository,
                          CategoryRepository theCategoryRepository){
        this.productRepository = theProductRepository;
        this.categoryRepository = theCategoryRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findByNameAndPrice (String name , Double price){
        return productRepository.findByNameAndPrice(name,price);
    }

    public List<Product> findByName (String name){
        return productRepository.findByName(name);
    }

    public List<Product> findByPrice (Double price){
        return productRepository.findByPrice(price);
    }

    public void addNew (ProductRequest productRequest){
        try {
            Product pro = new Product();
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() ->
                    new RuntimeException("Id Not Found"));
            pro.setName(productRequest.getName());
            pro.setSku(productRequest.getSku());
            pro.setCategoryId(category);
            pro.setPrice(productRequest.getPrice());
            pro.setDescription(productRequest.getDescription());
            pro.setCreatedAt(LocalDateTime.now());
            productRepository.save(pro);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity findById (Long id){
        if(productRepository.existsById(id)){
        Product product =  productRepository.findById(id).orElseThrow(()->
                    new RuntimeException("Id Not Found"));
        return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.status(400).body("Id Not Found");
        }

    }

    public ResponseEntity update (Long id, ProductRequest product){
        if (productRepository.existsById(id)){
            Product pro=productRepository.findById(id).orElseThrow(()->
                    new RuntimeException("Id Not Found"));

            if(product.getName() != null){
                pro.setName(product.getName());
            }
            if(product.getSku() != null){
                pro.setSku(product.getSku());
            }
            if (product.getCategoryId() != null){
                Category category=categoryRepository.findById(product.getCategoryId()).orElseThrow(()->
                        new RuntimeException("id Not Found"));
                pro.setCategoryId(category);

            }
            if(product.getPrice() != null){
                pro.setPrice(product.getPrice());
            }
            if(product.getDescription() != null){
                pro.setDescription(product.getDescription());
            }
            if (product.getUpdateAt() != null){
                pro.setUpdatedAt(product.getUpdateAt());
            }
            productRepository.save(pro);
            return ResponseEntity.ok(pro);
        }else{
            return ResponseEntity.status(400).body("Id Not Found");
        }
    }

    public ResponseEntity deleteById(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete By Id");
        }else{
            return ResponseEntity.status(400).body("Id Not Found");
        }
    }
}
