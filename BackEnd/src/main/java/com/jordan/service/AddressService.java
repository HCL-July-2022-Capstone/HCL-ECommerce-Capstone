package com.jordan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.model.Address;
import com.jordan.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	private Address shippingAddress;
	private Address billingAddress;
	
	public List<Address> getAddressesByUsername(String user){
		return addressRepository.findAllByUsername(user).get();
	}
	
	public void setShippingAddress(Address address, String userId) {
		shippingAddress = address;
		shippingAddress.setUsername(userId);
		addressRepository.save(shippingAddress);
	}
	
	public void setBillingAddress(Address address, String userId) {
		billingAddress = address;
		billingAddress.setUsername(userId);
		addressRepository.save(billingAddress);
	}
	
	public Address getShippingAddress() {
		return shippingAddress;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	
	public Address getAddress() {
		return shippingAddress;
	}
}
