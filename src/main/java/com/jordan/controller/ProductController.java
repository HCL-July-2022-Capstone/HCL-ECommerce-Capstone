package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Cart;
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
	
	@GetMapping("/getAllProducts")
//	@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> listallproducts() {
		return service.getAllProducts();
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product) {
		repo.save(product);

		return product.getProductName()+ " added to Inventory!";
	}
	
	@GetMapping("/get/{id}")
	public Optional<Product> getProductId(@PathVariable Integer id) {
		//Optional<Product> prod = service.getProductById(id);
		return service.getProductById(id);
	}
	
	@PutMapping("/get/{id}")
	public void UpdateProductById(@RequestBody Product product, @PathVariable Integer id) {
		//Optional<Product> prod = service.getProductById(id);
		repo.save(product);
	}
	
	@GetMapping("/getAllCategories")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<ProductCategory> listallCategories() {
		return service.getAllCategories() ;
	}
	
	@PostMapping("/addCategory")
	public String addCategory(@RequestBody ProductCategory productCategory) {
		catRepo.save(productCategory);

		return productCategory.getCategoryName() + " added to Categories!";
	}
	
	@GetMapping("/category/{id}")
	public Optional<ProductCategory> getProductCategoryById(@PathVariable Integer id) {
		//Optional<ProductCategory> prod = service.getCategoryById(id);
		return service.getCategoryById(id);
	}
	
	@PutMapping("/category/{id}")
	public void UpdateCategoryById(@RequestBody ProductCategory productCategory, @PathVariable Integer id) {
		//Optional<ProductCategory> cat = service.getCategoryById(id);
		catRepo.save(productCategory);
	}
	
}
