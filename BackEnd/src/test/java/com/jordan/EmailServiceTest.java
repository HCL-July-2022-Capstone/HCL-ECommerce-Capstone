package com.jordan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.jordan.model.Address;
import com.jordan.model.Orders;
import com.jordan.model.Product;
import com.jordan.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
	
	@Autowired
	EmailService emailService;
	
	@MockBean
	JavaMailSenderImpl mailSender;

	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		emailService = new EmailService();
		Mailbox.clearAll();
	}
	
	@Test
	void sendRegistrationEmailTest() throws MessagingException, IOException {
		String user = "TEST_USER@jordan.com";
		
		emailService.sendRegistrationEmail(user);
		
		List<Message> inbox = Mailbox.get("TEST_USER@jordan.com");
		
		assertEquals(1, inbox.size());
		assertEquals("Welcome to our super cool ecommerce app!", inbox.get(0).getSubject());
		assertEquals("Hello "+user+", welcome!", inbox.get(0).getContent());
	}
	
	@Test
	void sendConfirmationEmailTest() throws MessagingException, IOException {
		String user = "TEST_USER@jordan.com";
		
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		Product product2 = new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image");
		products.add(product1);
		products.add(product2);
		
		Address shippingAddress = new Address(1, "123 Test Blvd", "Test City", "Test State", "12345", "United States", "TEST_USER@jordan.com");
		Address billingAddress = new Address(2, "456 Test Blvd", "Test City", "TEST", "67890", "United States", "TEST_USER@jordan.com");
		
		Orders order = new Orders(1, 2399.98f, "TEST_ORDERSTATUS", "TEST_USER@jordan.com", products, shippingAddress, billingAddress);
		
		emailService.sendConfirmationEmail(user, order);
		
		List<Message> inbox = Mailbox.get("TEST_USER@jordan.com");
		
		assertEquals(1, inbox.size());
		assertEquals("Order #"+order.getOrderId(), inbox.get(0).getSubject());
		assertEquals("Your order has been placed. Total: "+order.getTotalPrice(), inbox.get(0).getContent());
	}
	
	@Test
	void sendInventoryStatusEmailTest() throws MessagingException, IOException {
		String prod = "TEST_PROD";
		
		emailService.sendInventoryStatustEmail(prod);
		
		List<Message> inbox = Mailbox.get("admin@gmail.com");
		
		assertEquals(1, inbox.size());
		assertEquals("Inventory Status" ,inbox.get(0).getSubject());
		assertEquals(prod, inbox.get(0).getContent());
	}
}
