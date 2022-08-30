package com.jordan.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	UserService userService;
	
	public Optional<Cart> getCartById(int id){
		return cartRepo.findById(id);
	}
	
	public void save(Cart cart) {
		cartRepo.save(cart);
	}
	
	public void addToCart(String username, int id) {
		User user = userService.getUserByUsername(username).get();
		user.getCart().addToCart(productService.getProductById(id).get());
		userService.save(user);
	}
	
	public List<Product> viewCart(String username) {
		User user = userService.getUserByUsername(username).get();
		logger.debug("Getting cart of user "+user);
		return user.getCart().getProducts();
		
	}
	
	public void removeFromCart(String username, int id) {
		User user = userService.getUserByUsername(username).get();
		user.getCart().removeFromCart(productService.getProductById(id).get());
		userService.save(user);
	}
	
	public void checkout(String username) {
		User user = userService.getUserByUsername(username).get();
		Cart cart = user.getCart();
		Orders order = new Orders();
		//build order from cart
		cart.getProducts().forEach(product -> order.addProduct(product));
		logger.warn("Getting products "+cart.getProducts().get(0).getProductName());
		logger.warn("Set products to "+order.getProducts().get(0).getProductName());
		order.setTotalPrice(cart.getTotalPrice());
		order.setOrderStatus("Order Placed");
		//TODO let the user select their address on the front end
		order.setBillingAddress(user.getAddresses().iterator().next());
		order.setShippingAddress(user.getAddresses().iterator().next());	
		//lower stock of each item - works
		order.getProducts().forEach(product ->{ 
			product.decreaseStock();
			productService.save(product);
		} );
		logger.warn("lowered product stock");
		user.getCart().emptyCart();
		user.addOrder(order);
		userService.save(user);
		emailService.sendConfirmationEmail(user,order);
		logger.warn("finished checkout");
		
	}
}

