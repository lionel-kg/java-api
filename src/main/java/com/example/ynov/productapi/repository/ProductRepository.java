package com.example.ynov.productapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ynov.productapi.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
 
}
