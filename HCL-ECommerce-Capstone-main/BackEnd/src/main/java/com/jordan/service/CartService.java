package com.jordan.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.model.*;
import com.jordan.repository.CartRepository;

@Service("cartService")
public class CartService
{
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

	@Autowired
	RestService restService;

	public Optional<Cart> getCartById(int id)
	{
		return cartRepo.findById(id);
	}

	public void save(Cart cart)
	{
		cartRepo.save(cart);
	}

	public void addToCart(Principal user, int id)
	{
		Cart cart = getCart(user);
		cart.addToCart(productService.getProductById(id).get());
		logger.info("added to " + user.getName() + "'s cart with id " + cart.getId());
		save(cart);
	}

	public List<Product> viewCart(Principal user)
	{
		return getCart(user).getProducts();
	}

	public void removeFromCart(Principal user, int id)
	{
		Cart cart = getCart(user);
		cart.removeFromCart(productService.getProductById(id).get());
		save(cart);
	}

	public void checkout(Principal user)
	{
		Cart cart = getCart(user);
		if (cart.getProducts().isEmpty())
		{
			logger.error("Cart is empty, cannot check out");
			return;
		}
		Orders order = new Orders();

		cart.getProducts().forEach(product -> order.addProduct(product));

		logger.warn("Getting products " + cart.getProducts().get(0).getProductName());
		logger.warn("Set products to " + order.getProducts().get(0).getProductName());
		order.setTotalPrice(cart.getTotalPrice());
		order.setOrderStatus("Order Placed");
		
		// TODO let the user select their address on the front end
		// on the frontend, user should have input addresses on initial checkout page
		// before hitting button
		// and it should be saved in the addressService (and in the repository)
		// frontend calls /setBillingAddress and /setCheckingAddress on a form in
		// checkout to do this

		if (addressService.getBillingAddress() == null || addressService.getShippingAddress() == null)
		{
			logger.error("User tried to check out without setting addresses");
			return;
		}
		order.setBillingAddress(addressService.getBillingAddress());
		order.setShippingAddress(addressService.getShippingAddress());

		restService.checkStock();
		// lower stock of each item - works
		order.getProducts().forEach(product ->
		{
			product.decreaseStock();
			productService.save(product);
		});
		logger.warn("lowered product stock");
		cart.emptyCart();
		order.setUser(user.getName());
		orderService.save(order);
		save(cart);
		emailService.sendConfirmationEmail(user.getName(), order);
		return;
	}

	private Cart getCart(Principal user)
	{
		Optional<Cart> maybeCart = cartRepo.findByUsername(user.getName());

		if (maybeCart.isEmpty())
		{
			Cart newCart = new Cart();
			newCart.setUsername(user.getName());
			return newCart;
		} else
			return maybeCart.get();
	}
}