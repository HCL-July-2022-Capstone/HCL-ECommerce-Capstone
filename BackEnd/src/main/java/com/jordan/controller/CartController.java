package com.jordan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.service.UserService;
import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.Product;
import com.jordan.model.User;
import com.jordan.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	Cart cart;

	@Autowired
	Orders order;
	@Autowired
	CartService cartService;
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
	public void checkout(@AuthenticationPrincipal User principal) {
		cartService.checkout(principal);
	}

}
