package com.jordan.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jordan.model.*;
import com.jordan.repository.CartRepository;
import com.jordan.service.OrdersService;

@Service("cartService")
@Component
public class CartService {
	private Logger logger = LoggerFactory.getLogger(CartService.class);

	@Autowired
	CartRepository cartRepo;

	@Autowired
	OrdersService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	AddressService addressService;

	public Optional<Cart> getCartById(int id) {
		return cartRepo.findById(id);
	}

	public void save(Cart cart) {
		cartRepo.save(cart);
	}

	public void addToCart(Principal user, int id) {
		Cart cart = getCart(user);
		cart.addToCart(productService.getProductById(id).get());
		logger.info("added to "+user.getName()+"'s cart with id "+cart.getId());
		save(cart);

	}

	public List<Product> viewCart(Principal user) {
		return getCart(user).getProducts();

	}

	public void removeFromCart(Principal user, int id) {
		Cart cart = getCart(user);
		cart.removeFromCart(productService.getProductById(id).get());
		save(cart);
	}

	public String checkout(Principal user) {
		Cart cart = getCart(user);
		if(cart.getProducts().isEmpty()) {
			return "Checkout failed: cart is empty!";
		}
		Orders order = new Orders();

		cart.getProducts().forEach(product -> order.addProduct(product));

		logger.warn("Getting products " + cart.getProducts().get(0).getProductName());
		logger.warn("Set products to " + order.getProducts().get(0).getProductName());
		order.setTotalPrice(cart.getTotalPrice());
		order.setOrderStatus("Order Placed");
		// TODO let the user select their address on the front end
		List<Address> userAddresses = addressService.getAddressesByUsername(user.getName());
		if(userAddresses.isEmpty()) {
			return "Checkout failed: user has no addresses";
		}
		order.setBillingAddress(userAddresses.get(0));
		order.setShippingAddress(userAddresses.get(0));
		// lower stock of each item - works
		order.getProducts().forEach(product -> {
			product.decreaseStock();
			productService.save(product);
		});
		logger.warn("lowered product stock");
		cart.emptyCart();
		order.setUser(user.getName());
		orderService.save(order);
		save(cart);
		emailService.sendConfirmationEmail(user.getName(), order);
		return "Order placed!";

	}

	private Cart getCart(Principal user) {
		Optional<Cart> maybeCart = cartRepo.findByUsername(user.getName());
		if (maybeCart.isEmpty()) {
			Cart newCart = new Cart();
			newCart.setUsername(user.getName());
			return newCart;
		} else
			return maybeCart.get();
	}
}
