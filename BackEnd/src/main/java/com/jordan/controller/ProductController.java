package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/addProduct")
	private String addProduct(@RequestBody Product product) {

        product.setProductId(product.getProductId());
        product.setCategoryId(product.getCategoryId());
        product.setCategoryName(product.getCategoryName());
        product.setProductName(product.getProductName());
        product.setProductPrice(product.getProductPrice());
        product.setQuantityOnHand(product.getQuantityOnHand());
        repo.save(product);

        return product.getProductName() + " has been added to inventory!";
    }
	
	@PostMapping("/getAllProducts")
	public List<Product> listallproducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Product> getProductId(@PathVariable Integer id) {
		return service.getProductById(id);
	}
	
	@PutMapping("/get/{id}")
	private ResponseEntity<Product> updateProductById(@PathVariable Integer id,
                                                      @RequestBody Product product) {
        Optional<Product> idOfProduct = repo.findById(id);

        if (idOfProduct.isPresent()) { //if product id is in db

            Product updateProduct = idOfProduct.get();

            updateProduct.setCategoryId(product.getCategoryId());
            updateProduct.setCategoryName(product.getCategoryName());
            updateProduct.setProductName(product.getProductName());
            updateProduct.setProductPrice(product.getProductPrice());
            updateProduct.setQuantityOnHand(product.getQuantityOnHand());

            return new ResponseEntity<>(repo.save(updateProduct),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	//delete a product
    @DeleteMapping("/deleteproduct/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {

        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
