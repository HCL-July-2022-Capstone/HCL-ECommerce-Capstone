package com.jordan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jordan.model.Orders;
import com.jordan.repository.OrdersRepository;
import com.jordan.service.OrdersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTest {
	
	@Autowired
	OrdersService ordersService;
	
	@MockBean
	OrdersRepository ordersRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getOrdersTest() {
		when(ordersRepository.findAll()).thenReturn(Stream.of(
			new Orders(1, 2000f, "Shipped", null, null, null, null),
			new Orders(2, 3000f, "Delivered", null, null, null, null))
				.collect(Collectors.toList()));
		assertEquals(2, ordersService.getOrders().size());
	}
	
	@Test
	void getOrdersByIdTest() {
		int id = 1;
		Orders orders1 = new Orders(1, 2000f, "Shipped", null, null, null, null);
		Optional<Orders> orders = Optional.of(orders1);
		when(ordersRepository.findById(id)).thenReturn(orders);
		assertThat(orders).isNotNull();
		assertEquals(orders1, ordersService.getOrderById(id).get());
	}	
	

	@Test
	void saveOrderTest() {
		Orders orders = new Orders(1, 2000f, "Shipped", null, null, null, null);
		
		ordersService.save(orders);
		verify(ordersRepository, times(1)).save(orders);
	}
	
	@Test
	void deleteOrderTest() {
		Orders orders1 = new Orders(1, 2000f, "Shipped", null, null, null, null);
		
		ordersService.deleteOrder(1);
		verify(ordersRepository, times(1)).deleteById(1);
	}
}