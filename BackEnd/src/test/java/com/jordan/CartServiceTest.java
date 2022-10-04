package com.jordan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jordan.model.Address;
import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.Product;
import com.jordan.repository.CartRepository;
import com.jordan.repository.OrdersRepository;
import com.jordan.service.AddressService;
import com.jordan.service.CartService;
import com.jordan.service.OrdersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest
{
	@Autowired
	CartService cartService;

	@Autowired
	AddressService addressService;

	@Autowired
	OrdersService ordersService;

	@MockBean
	CartRepository cartRepository;

	@MockBean
	OrdersRepository ordersRepository;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getCartByIdTest()
	{
		int id = 1;

		List<Product> products = new ArrayList<>();
		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		products.add(product);

		Cart cart1 = new Cart(1, products, "TEST");
		Optional<Cart> cart = Optional.of(cart1);

		when(cartRepository.findById(id)).thenReturn(cart);

		assertThat(cart).isNotNull();
		assertEquals(cart1, cartService.getCartById(id).get());
	}

	@Test
	void saveCartTest()
	{
		Cart cart = new Cart(1, null, "TEST");

		cartService.save(cart);

		verify(cartRepository, times(1)).save(cart);
	}

	@Test
	void addToCartTest()
	{
		Principal mockPrincipal = mock(Principal.class);

		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		Product product2 = new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image");
		products.add(product1);
		products.add(product2);

		Cart cart = new Cart(1, products, "TEST");

		Optional<Cart> optionalCart = Optional.of(cart);

		when(mockPrincipal.getName()).thenReturn("TEST");
		when(cartRepository.findByUsername("TEST")).thenReturn(optionalCart);

		cartService.addToCart(mockPrincipal, 1);
		assertEquals(products.get(0), cart.getProducts().get(0));
	}

	@Test
	void viewCartTest()
	{
		Principal mockPrincipal = mock(Principal.class);

		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		Product product2 = new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image");
		products.add(product1);
		products.add(product2);

		Cart cart = new Cart(1, products, "TEST");
		Optional<Cart> optionalCart = Optional.of(cart);

		when(mockPrincipal.getName()).thenReturn("TEST");
		when(cartRepository.findByUsername("TEST")).thenReturn(optionalCart);

		assertEquals(products, cartService.viewCart(mockPrincipal));
	}

	@Test
	void removeFromCartTest()
	{
		int id = 1;
		Principal mockPrincipal = mock(Principal.class);
		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		Product product2 = new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image");
		products.add(product1);
		products.add(product2);

		Cart cart = new Cart(1, products, "TEST");
		Optional<Cart> optionalCart = Optional.of(cart);

		when(mockPrincipal.getName()).thenReturn("TEST");
		when(cartRepository.findByUsername("TEST")).thenReturn(optionalCart);

		cartService.removeFromCart(mockPrincipal, id);

		assertEquals(products.get(0), cart.getProducts().get(0));
	}

//	@Test
//	void checkoutTest()
//	{
//		Principal mockPrincipal = mock(Principal.class);
//		List<Product> products = new ArrayList<Product>();
//		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
//		Product product2 = new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image");
//		products.add(product1);
//		products.add(product2);
//
//		Cart cart = new Cart(1, products, "TEST_USERNAME");
//		Optional<Cart> optionalCart = Optional.of(cart);
//
//		Address shippingAddress = new Address("123 Test Blvd", "Test City", "Test State", "12345", "United States",
//				"TEST_USERNAME");
//		Address billingAddress = new Address("456 Test Blvd", "Test City", "Test State", "67890", "United States",
//				"TEST_USERNAME");
//
//		addressService.addAddress(shippingAddress, "TEST_USERNAME");
//		addressService.addAddress(billingAddress, "TEST_USERNAME");
//
//		addressService.setBillingAddress(billingAddress);
//		addressService.setShippingAddress(shippingAddress);
//
//		Orders order = new Orders(1, 2399.98f, "TEST_ORDERSTATUS", "TEST_USERNAME", products, shippingAddress,
//				billingAddress);
//
//		when(mockPrincipal.getName()).thenReturn("TEST_USERNAME");
//		when(cartRepository.findByUsername("TEST_USERNAME")).thenReturn(optionalCart);
//
//		ordersRepository.save(order);
//		verify(ordersRepository, times(1)).save(order);
//
//		cartService.checkout(mockPrincipal);
//
//		assertEquals(true, cart.IsEmpty());
//	}
}