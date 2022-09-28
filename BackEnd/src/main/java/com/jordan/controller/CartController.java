package com.jordan.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Address;
import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.Product;
import com.jordan.service.AddressService;
import com.jordan.service.CartService;
import com.jordan.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController
{
	@Autowired
	CartService cartService;

	@Autowired
	AddressService addressService;

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	private Logger logger = LoggerFactory.getLogger(CartController.class);

	@GetMapping("/view")
	public List<Product> viewCart(Principal user)
	{
		return cartService.viewCart(user);
	}

	@PostMapping("/add/{id}")
	public void addToCart(Principal user, @PathVariable int id)
	{
		cartService.addToCart(user, id);
	}

	@DeleteMapping("/remove/{id}")
	public void removeFromCart(Principal user, @PathVariable int id)
	{
		cartService.removeFromCart(user, id);
	}

	@PostMapping("/checkout")
	public void checkout(Principal user)
	{
		cartService.checkout(user);
	}
}