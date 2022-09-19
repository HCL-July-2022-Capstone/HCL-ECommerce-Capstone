package com.jordan;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jordan.model.Address;
import com.jordan.repository.AddressRepository;
import com.jordan.service.AddressService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
	
	@Autowired
	AddressService addressService;
	
	@MockBean
	AddressRepository addressRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getAddressesByUsernameTest() {
		String username = "TEST_USERNAME";
		Address address1 = new Address(1, "123 Test Blvd", "Test City", "TEST", "12345", "United States", "TEST_USERNAME");
		Address address2 = new Address(2, "456 Test Blvd", "Test City", "TEST", "67890", "United States", "TEST_USERNAME");
		List<Address> addressList = new ArrayList<>();
		addressList.add(address1);
		addressList.add(address2);
		
		when(addressRepository.findAllByUsername(username)).thenReturn(addressList);
		
		assertEquals(2, addressService.getAddressesByUsername(username).size());
	}
	
	@Test
	void setShippingAddressTest() {
		Address shippingAddress = new Address(1, "123 Test Blvd", "Test City", "TEST", "12345", "United States", "TEST_USERNAME");
		addressService.setShippingAddress(shippingAddress);
		
		assertEquals("123 Test Blvd", shippingAddress.getStreet());

	}
	
	@Test
	void setBillingAddressTest() {
		Address billingAddress = new Address(2, "456 Test Blvd", "Test City", "TEST", "67890", "United States", "TEST_USERNAME");
		addressService.setBillingAddress(billingAddress);
		
		assertEquals("456 Test Blvd", billingAddress.getStreet());
	}
	
	@Test
	void addAddressTest() {
		Address address = new Address(1, "123 Test Blvd", "Test City", "TEST", "12345", "United States", "TEST_USERNAME");
		String username = "TEST_USERNAME";
		addressService.addAddress(address, username);
		
		when(addressRepository.save(address)).thenReturn(address);
		
		assertEquals("123 Test Blvd", address.getStreet());
		verify(addressRepository, times(1)).save(address);
	}
	
}
