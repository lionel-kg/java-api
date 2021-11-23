package com.example.ynov.productapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ynov.productapi.model.Product;
import com.example.ynov.productapi.repository.ProductRepository;
import com.example.ynov.productapi.service.ProductService;

@RestController
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public Iterable<Product> getProducts() 
	{
		return productService.getProducts();
		
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) 
	{
		Optional<Product> p = productService.getProduct(id);
		if(p.isPresent()) {
			return new ResponseEntity<Product>(p.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("product/add")
	public Product addProduct(@RequestBody Product produit) 
	{
		return productService.upsert(produit);
	}
	
	@DeleteMapping("product/{id}")
	public void deleteProduct(@PathVariable("id") Integer id)
	{
		productService.deleteProduct(id);
	}
	
	@PutMapping("product/edit")
	public Product editProduct(@RequestBody Product produit) {
		return productService.upsert(produit);
	}
	
	@PatchMapping("/product")
	public ResponseEntity<Product> partialEditProduct(@RequestBody Product produit) {
		Optional<Product> p = productService.getProduct(produit.getId());
		if(p.isPresent()) {
			Product existingProduct = p.get();
			if(produit.getName() != null && !produit.getName().equals(existingProduct.getName())) {
				existingProduct.setName(produit.getName());
			}
			if(produit.getDescription() != null && !produit.getDescription().equals(existingProduct.getDescription())) {
				existingProduct.setDescription(produit.getDescription());
			}

			if(produit.getCost() != null && !produit.getCost().equals(existingProduct.getCost())) {
				existingProduct.setCost(produit.getCost());
			}
			existingProduct = productService.upsert(existingProduct);
			return new ResponseEntity<Product>(existingProduct,HttpStatus.OK);
		}
		 	return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	
	@GetMapping("product-filter/{name}")
	public Iterable<Product> getProductFilterName(@PathVariable("name") String name){
		return productService.getProductByName(name);
	}
	
		
		
	

}
