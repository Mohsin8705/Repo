package com.sm.backend.Serach;

import com.sm.backend.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class SerachByNameAndPrice {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findByNameAndPrice (String name , Double price){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> pre = new ArrayList<>();
        if (name != null && !name.isEmpty()){
            pre.add(cb.equal(root.get("name"),name));
        }
        if (price != null){
            pre.add(cb.equal(root.get("price"),price));
        }
        cq.where(cb.and(pre.toArray(new Predicate[0])));
        return entityManager.createQuery(cq).getResultList();
    }
}
