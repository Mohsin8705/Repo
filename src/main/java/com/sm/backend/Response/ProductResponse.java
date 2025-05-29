package com.sm.backend.Response;

import com.sm.backend.model.Product;

import java.time.LocalDateTime;

public class ProductResponse {
    private Long id;
    private String name;
    private String sku;
    private CategoryResponse categoryId;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.sku = product.getSku();
        this.categoryId = new CategoryResponse(product.getCategoryId());
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.createdAt = product.getCreatedAt();
        this.updateAt = product.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public CategoryResponse getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryResponse categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
