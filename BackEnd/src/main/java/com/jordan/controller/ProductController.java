package com.jordan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Product;
import com.jordan.model.ProductCategory;
import com.jordan.repository.CategoryRepository;
import com.jordan.repository.ProductRepository;
import com.jordan.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
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
	public List<Product> listallproducts() {
		
		return service.getAllProducts();
	}
	
	@GetMapping("/Search/{productName}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String productName){
		try {
			List<Product> products = new ArrayList<Product>();
			
			if(productName == null) {
				repo.findAll().forEach(products::add);
			}
			
			else {
				repo.findByproductNameContaining(productName).forEach(products::add);
			}
			
			if(products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product) {
		product = repo.save(product);

		return product.getProductName()+ " added to Inventory!";
	}
	
	@GetMapping("/get/{id}")
	public Optional<Product> getProductId(@PathVariable Integer id) {
		return service.getProductById(id);
	}
	
	@PutMapping("/get/{id}")
	public void UpdateProductById(@RequestBody Product product, @PathVariable Integer id) {
		repo.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Integer id) {
		repo.deleteById(id);
	}
	@GetMapping("/getAllCategories")
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
		return service.getCategoryById(id);
	}
	
	@PutMapping("/category/{id}")
	public void UpdateCategoryById(@RequestBody ProductCategory productCategory, @PathVariable Integer id) {
		catRepo.save(productCategory);
	}
	

}
