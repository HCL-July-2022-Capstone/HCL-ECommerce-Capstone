package com.jordan.service;


import com.jordan.model.Product;
import com.jordan.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductService {
    @Autowired
    ProductRepository repo;

    public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public Optional<Product> getProductById(int id) {
		return repo.findById(id);
	}

	public Optional<Product> searchByProductName(String productName) {
		return repo.searchByProductName(productName);
	}

	public void deleteProduct(int id) {
		repo.deleteById(id);
	}
}
