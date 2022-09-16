package com.jordan.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	
	private Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	public List<Address> getAddressesByUsername(String user){
		return addressRepository.findAllByUsername(user).get();
	}
	
	public void addAddress(Address address, String username) {
		address.setUsername(username);
		if(addressRepository.findById(address.getId()).isEmpty())addressRepository.save(address);
		logger.warn("Added new address with ID "+address.getId());
	}
	
	//selects address
	public void setShippingAddress(Address address) {
		shippingAddress = address;
		logger.warn("SET shipping address");
	}
	
	public void setBillingAddress(Address address) {
		billingAddress = address;
		logger.warn("set billing address");
	}
	
	public void deleteAddress(Address address) {
		addressRepository.delete(address);
		logger.warn("deleted address with id "+address.getId());
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
