package com.productservice.services;

import java.util.List;

import com.productservice.model.Product;

public interface ProductService {
	Product addProduct(Product product);

	Product updateProduct(Product product);

	Product getProduct(int id);

	List<Product> getAllProducts();

	void deleteProduct(int id);

	List<Product> getByName(String productName);
}
