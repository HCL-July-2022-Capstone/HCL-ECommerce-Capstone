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
		return addressRepository.findAllByUsername(user);
	}
	
	public void setShippingAddress(Address address, String userId) {
		shippingAddress = address;
		shippingAddress.setUsername(userId);
		//addressRepository.save(shippingAddress);
		logger.warn("SET shipping address");
	}
	
	public void setBillingAddress(Address address, String userId) {
		billingAddress = address;
		billingAddress.setUsername(userId);
		//addressRepository.save(billingAddress);
		logger.warn("set billing address");
	}
	
    public Address addAddress(Address address, String username) {
        address.setUsername(username);
        if(!addressRepository.findById(address.getId()).isPresent()) addressRepository.save(address);
        logger.warn("Added new address with ID "+address.getId());
        return address;
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
