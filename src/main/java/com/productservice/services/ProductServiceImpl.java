package com.productservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public Product getProduct(int id) {

		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(int id) {

		productRepository.deleteById(id);

	}

	@Override
	public List<Product> getByName(String productName) {

		return productRepository.findByProductNameContaining(productName);
	}

}
