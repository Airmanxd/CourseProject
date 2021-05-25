package com.example.coursework.services;

import com.example.coursework.models.Product;
import com.example.coursework.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public boolean addProduct(Product product) throws IOException {
        log.info(String.format("Trying to add a product %s", product.getName()));
        if(repository.findByName(product.getName()) != null)
            return false;
        repository.save(product);
        return true;
    }
    public List<Product> getAll()
    {
        return repository.findAll();
    }

    public List<Product> getByCategory(String category)
    {
        return repository.findByCategory(category);
    }

    public boolean deleteProduct(Long id) {
        Product product = repository.findByProductId(id);
        if(product == null)
            return false;
        repository.delete(product);
        return true;
    }
}
