package com.jordan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Product;
import com.jordan.model.ProductCategory;
import com.jordan.repository.CategoryRepository;
import com.jordan.repository.ProductRepository;
import com.jordan.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@GetMapping("/getAll")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> listallusers() {
		return service.getAllProducts();
	}
	
	@PostMapping("/addProduct")
	public String join(@RequestBody Product product) {
		repo.save(product);

		return product.getProductName()+ " added to Inventory!";
	}
	
	@GetMapping("/getAllCategories")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<ProductCategory> listallCategories() {
		return service.getAllCategories() ;
	}
	
	@PostMapping("/addCategory")
	public String join(@RequestBody ProductCategory productCategory) {
		catRepo.save(productCategory);

		return productCategory.getCategoryName() + " added to Categories!";
	}
}
