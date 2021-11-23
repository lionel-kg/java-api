package com.example.ynov.productapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ynov.productapi.model.Comment;
import com.example.ynov.productapi.model.Product;
import com.example.ynov.productapi.repository.CommentRepository;
import com.example.ynov.productapi.service.ProductService;

@RestController
public class CommentController {
	
	@Autowired
	ProductService productService; 
	
	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping("product/comment/add/{id}")
	public Product addComment(@RequestBody Comment comment,@PathVariable("id") Integer id) {
		
		Product existingProduct = productService.getProduct(id).get();
		comment.setId_product(existingProduct.getId());
		existingProduct.getComments().add(comment);
		return productService.upsert(existingProduct);
		
	}
}
