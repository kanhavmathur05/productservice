package com.productservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.exception.ResourceNotFoundException;
import com.productservice.model.Product;
import com.productservice.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {
 
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@PostMapping("/add-product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ResourceNotFoundException {
		Product addedProduct = productService.addProduct(product);
		if (addedProduct == null) {
			logger.error("Something went wrong while trying to add new product");
			throw new ResourceNotFoundException("Unable to add product");
		} else {
			logger.info("New Product details:- ", addedProduct);
			return ResponseEntity.ok(addedProduct); 
		}
	}

	@GetMapping("/get-product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) throws ResourceNotFoundException {
		logger.info("this is log product id",id);
		Product product = productService.getProduct(id);
		if (product == null) {
			logger.error("Something went wrong. Unable to get product for ID: ", id);
			throw new ResourceNotFoundException("Unable to get product for ID" + id); 
		} else { 
			logger.info("Product details:- ", product);
			return ResponseEntity.ok().body(product);
		}
	}

	@GetMapping("/get-all-products")
	public ResponseEntity<List<Product>> getAllProducts() throws ResourceNotFoundException {
		List<Product> productList = productService.getAllProducts();
		logger.info("List of Products:- ", productList); 
		if (productList == null) {
			throw new ResourceNotFoundException("Unable to get any resource");
		} else {
			return ResponseEntity.ok().body(productList);
		}
	}

	@GetMapping("/get-products/{query}")
	public ResponseEntity<List<Product>> getProductBySearch(@PathVariable String query)
			throws ResourceNotFoundException {
		List<Product> productList = productService.getByName(query);
		return ResponseEntity.ok().body(productList);
	}

	@DeleteMapping("/delete-product/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}

	@PutMapping("/update-product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ResourceNotFoundException {
		Product updatedProduct = productService.updateProduct(product);
		if (updatedProduct == null) {
			throw new ResourceNotFoundException("Unablet to update the product");
		} else {
			logger.info("Successfully updated the product details");
			return ResponseEntity.ok().body(updatedProduct);
		}

	}
}
