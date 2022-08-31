package com.jordan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	CartService cartService;
	@GetMapping("/view")
	public List<Product> viewCart(Principal principal) {
		return cartService.viewCart(principal.getName());
	}
	
	@PostMapping("/add/{id}")
	public void addToCart(Principal principal, @PathVariable int id) {
		cartService.addToCart(principal.getName(), id);
	}
	
	@DeleteMapping("/remove/{id}")
	public void removeFromCart(Principal principal, @PathVariable int id) {
		cartService.removeFromCart(principal.getName(), id);

	}

	
	@PostMapping("/checkout")
	public void checkout(Principal principal) {
		cartService.checkout(principal.getName());
	}

}
