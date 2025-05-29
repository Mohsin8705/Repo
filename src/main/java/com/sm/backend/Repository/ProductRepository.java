package com.sm.backend.Repository;

import com.sm.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

     List<Product> findByNameAndPrice (String name , Double price);
     List<Product> findByName (String name);
     List<Product> findByPrice (Double price);

}
