package com.jordan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jordan.model.Address;
import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.Product;
import com.jordan.model.Roles;
import com.jordan.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@MockBean
	RestTemplate restTemplate;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void getUsernameTest() {
//		String url = "https://dev-32668171.okta.com/oauth2/v1/userinfo";
//		when(restTemplate.getForObject(url, String.class)).thenReturn();
//	}
//	
//	@Test
//	void getTokenTest() {
//		
//	}
//	@Test
//	void getAllUsersTest() {
//
//		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
//		products.add(product);
//		
//		Cart cart = new Cart(1, products);
//		
//    	Address address = new Address(10, "Test St", "312", "San Antonio", "TX", "78531", "United States of America");
//    	addresses.add(address);
//    	
//    	Orders order = new Orders(1, 27.02f, "SHIPPED", products, address, address, null);
//    	orders.add(order);
//
//    	Roles role = new Roles(1, "ADMIN");
//    	roles.add(role);
//    	
//		when(userRepository.findAll()).thenReturn(Stream.of(
//				new User(1, "claudioord1", "123", "Claudio", "Ordaz", "1234567890", cart, roles, orders, addresses), 
//				new User(2, "davidgom2", "123", "David", "Gomez", "999939492", cart, roles, orders, addresses))
//				.collect(Collectors.toList()));
//		assertEquals(2, userService.getAllUsers().size());
//	}
//	
//	@Test
//	void getUserByIdTest() {
//		int id = 1;
//		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image", null);
//		products.add(product);
//		
//		Cart cart = new Cart(1, products);
//		
//    	Address address = new Address(10, "Test St", "312", "San Antonio", "TX", "78531", "United States of America", "999939492", null);
//    	addresses.add(address);
//    	
//    	Orders order = new Orders(1, 27.02f, "SHIPPED", products, address, address, null);
//    	orders.add(order);
//
//    	Roles role = new Roles(1, "ADMIN");
//    	roles.add(role);
//    	
//    	User user1 = new User(1, "claudioord1", "123", "Claudio", "Ordaz", "1234567890", cart, roles, orders, addresses);
//    	Optional<User> user = Optional.of(user1); 
//    	
//		when(userRepository.findById(id)).thenReturn(user);
//		assertThat(user).isNotNull();
//		assertEquals(user1, userService.getUserById(id).get());
//		
//	}
//	
//	@Test
//	void getUserByUsernameTest() {
//		String username = "claudioord1";
//		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image", null);
//		products.add(product);
//		
//		Cart cart = new Cart(1, products);
//		
//    	Address address = new Address(10, "Test St", "312", "San Antonio", "TX", "78531", "United States of America", "999939492", null);
//    	addresses.add(address);
//    	
//    	Orders order = new Orders(1, 27.02f, "SHIPPED", products, address, address, null);
//    	orders.add(order);
//
//    	Roles role = new Roles(1, "ADMIN");
//    	roles.add(role);
//    	
//    	User user1 = new User(1, "claudioord1", "123", "Claudio", "Ordaz", "1234567890", cart, roles, orders, addresses);
//    	Optional<User> user = Optional.of(user1); 
//    	
//		when(userRepository.findByUsername(username)).thenReturn(user);
//		assertThat(user).isNotNull();
//		assertEquals(user1, userService.getUserByUsername(username).get());
//	}
//	
//	@Test
//	void saveUserTest() {
//		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image", null);
//		products.add(product);
//		
//		Cart cart = new Cart(1, products);
//		
//    	Address address = new Address(10, "Test St", "312", "San Antonio", "TX", "78531", "United States of America", "999939492", null);
//    	addresses.add(address);
//    	
//    	Orders order = new Orders(1, 27.02f, "SHIPPED", products, address, address, null);
//    	orders.add(order);
//
//    	Roles role = new Roles(1, "ADMIN");
//    	roles.add(role);
//    	
//    	User user1 = new User(1, "claudioord1", "123", "Claudio", "Ordaz", "1234567890", cart, roles, orders, addresses);
//    	
//    	userService.save(user1);
//    	verify(userRepository, times(1)).save(user1);
//	}
//	
//	@Test
//	void deleteUserTest() {
//		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image", null);
//		products.add(product);
//		
//		Cart cart = new Cart(1, products);
//		
//    	Address address = new Address(10, "Test St", "312", "San Antonio", "TX", "78531", "United States of America", "999939492", null);
//    	addresses.add(address);
//    	
//    	Orders order = new Orders(1, 27.02f, "SHIPPED", products, address, address, null);
//    	orders.add(order);
//
//    	Roles role = new Roles(1, "ADMIN");
//    	roles.add(role);
//    	
//    	User user1 = new User(1, "claudioord1", "123", "Claudio", "Ordaz", "1234567890", cart, roles, orders, addresses);
//    	
//    	userService.deleteUser(1);
//    	verify(userRepository, times(1)).deleteById(1);
//	}
}