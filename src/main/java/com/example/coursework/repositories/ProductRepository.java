package com.example.coursework.repositories;

import com.example.coursework.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);
    Product findByName(String name);
    List<Product> findByCategory(String category);
}
