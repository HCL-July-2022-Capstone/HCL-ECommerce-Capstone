package com.jordan.controller;

import com.jordan.model.Product;
import com.jordan.repository.ProductRepository;
import com.jordan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;
    @Autowired
	private ProductService service;
//    @Autowired
//	private CategoryRepository catRepo;

    //create new prouct
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

    //post all products
    @GetMapping("/getallproducts")
    private List<Product> listAllProducts() {

        return service.getAllProducts();
    }

    //find by productId
    @GetMapping("/get/{id}")
    private Optional<Product> getProductId(@PathVariable Integer id) {

        return service.getProductById(id);
    }

    //search by productName for frontend searchbox
    @GetMapping("/getproduct/{productName}")
    private Optional<Product> getProductName(@PathVariable String productName) {

        return service.searchByProductName(productName);
    }

    //update a product
    @PutMapping("/updateproduct/{id}")
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
}
