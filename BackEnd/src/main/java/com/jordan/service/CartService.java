package com.jordan.service;

import java.security.Principal;
import java.util.Date;
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
	
	public Optional<Cart> getCartById(int id){
		return cartRepo.findById(id);
	}
	
	public void save(Cart cart) {
		cartRepo.save(cart);
	}
	
	public void checkout(User user) {
		Cart cart = user.getCart();
		Orders order = new Orders();
		//build order from cart
		order.setProduct(cart.getProducts());
		order.setTotalPrice(cart.getTotalPrice());
		order.setOrderStatus("Order Placed");
		//TODO let the user select their address on the front end
		order.setBillingAddress(cart.getUser().getAddresses().iterator().next());
		order.setShippingAddress(cart.getUser().getAddresses().iterator().next());
		//lower stock of each item
		order.getProduct().forEach(product ->{
			product.decreaseStock();
			productService.save(product);
		} );
		if(orderService.checkout(order) == 0) {
			cartRepo.delete(cart);
			emailService.sendConfirmationEmail(order);
		}
		
	}
}

