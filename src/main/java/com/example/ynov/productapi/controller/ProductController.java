package com.example.ynov.productapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ynov.productapi.model.Product;
import com.example.ynov.productapi.repository.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/product")
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/product/{id}")
	public Optional<Product> getProduct(@PathVariable("id") Integer id) {
		return productRepository.findById(id);
	}

}
