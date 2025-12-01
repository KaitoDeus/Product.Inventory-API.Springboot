package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContainingIgnoreCase(String name);

    List<Product> findByCategoryCategoryNameContainingIgnoreCase(String categoryName);

    List<Product> findByProductNameContainingIgnoreCaseAndCategoryCategoryNameContainingIgnoreCase(String name,
            String categoryName);
}
