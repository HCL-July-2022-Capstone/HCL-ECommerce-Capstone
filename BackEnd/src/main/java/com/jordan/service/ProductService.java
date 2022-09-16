package com.jordan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.model.Product;
// import com.jordan.model.ProductCategory;
// import com.jordan.repository.CategoryRepository;
import com.jordan.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repo;
	
// 	@Autowired
// 	CategoryRepository CatRepo;
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	public Optional<Product> getProductById(int id) {
		return repo.findById(id);
	}

	public void deleteProduct(int id) {
		repo.deleteById(id);
	}
	
// 	public List<ProductCategory> getAllCategories() {
// 		return CatRepo.findAll();
// 	}
	
// 	public Optional<ProductCategory> getCategoryById(int id) {
// 		return CatRepo.findById(id);
// 	}

// 	public void deleteCategory(int id) {
// 		CatRepo.deleteById(id);
// 	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public List<Product> getCategory(String catName) {
		return repo.getByCategoryName(catName);
	}
	
	
}
