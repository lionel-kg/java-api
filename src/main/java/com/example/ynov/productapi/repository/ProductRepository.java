package com.example.ynov.productapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.ynov.productapi.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
 
	public Iterable<Product> findByName(String name);
	
	@Query(value = "SELECT * FROM products WHERE name = :name ", nativeQuery = true)
	public Iterable<Product> findByNameNative(@Param("name") String name);
}
