package com.sm.backend.Service;

import com.sm.backend.Repository.CategoryRepository;
import com.sm.backend.Request.CategoryRequest;
import com.sm.backend.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository theCategoryRepository){
        categoryRepository = theCategoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void addNew (CategoryRequest category){

        Category cat=new Category();

        Category category1=categoryRepository.findById(category.getParentId()).orElseThrow(()->
                new RuntimeException("Id Not Found"));
        cat.setName(category.getName());
        cat.setDescription(category.getDescription());
        cat.setParentId(category1);

        categoryRepository.save(cat);

    }
    public Category findById (Long id){
        return categoryRepository.findById(id).orElseThrow(()->
                    new RuntimeException("Id Not Found"));

        }




    public ResponseEntity update (Long id, CategoryRequest category){
        if (categoryRepository.existsById(id)){
            Category cat = categoryRepository.findById(id).orElseThrow(()->
                    new RuntimeException("Id Not Found"));

            if (category.getName() != null){
                cat.setName(category.getName());
            }
            if (category.getDescription() != null){
                cat.setDescription(category.getDescription());
            }
            if (category.getParentId() != null){
                Category category1=categoryRepository.findById(category.getParentId())
                        .orElseThrow(()->new RuntimeException("Id Not Found"));
                cat.setParentId(category1);
            }
            categoryRepository.save(cat);
            return ResponseEntity.status(200).body("Update Succesfull");
        }else{
            return ResponseEntity.status(400).body("Id Not Found");
        }
    }

    public ResponseEntity deleteById (Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete Succsefull");
        }else{
            return ResponseEntity.status(400).body("Id Not Found");
        }
    }
}
