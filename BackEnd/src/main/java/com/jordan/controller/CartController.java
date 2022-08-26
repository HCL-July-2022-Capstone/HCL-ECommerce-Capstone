package com.jordan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.Product;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	Cart cart;
	
	@Autowired
	Orders order;
	
	@GetMapping("/view")
	public List<Product> viewCart() {
		return cart.getProducts();
	}
	
	@PostMapping("/add")
	public void addToCart(@RequestBody Product product) {
		cart.addToCart(product);
	}
	
	@DeleteMapping("/remove")
	public void removeFromCart(@RequestBody Product product) {
		cart.removeFromCart(product);
	}
	
	@PostMapping("/checkout")
	public void checkout() {
		order.setProduct(cart.getProducts());
	}
}
