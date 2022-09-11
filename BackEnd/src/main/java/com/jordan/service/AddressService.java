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
	
	public List<Address> getAddressesByUsername(String user){
		return addressRepository.findAllByUsername(user).get();
	}
}
