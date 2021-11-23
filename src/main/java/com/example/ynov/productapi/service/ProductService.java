package com.example.ynov.productapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ynov.productapi.model.Product;
import com.example.ynov.productapi.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product upsert(Product product) 
	{
		return productRepository.save(product);
	}
	
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> getProduct(Integer id) 
	{
	
		return productRepository.findById(id);
	}
	
	
	public void deleteProduct(Integer id)
	{
		productRepository.deleteById(id);
	}

	public Iterable<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}
	
	
	
}
