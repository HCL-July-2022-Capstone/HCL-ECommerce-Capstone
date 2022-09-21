package com.jordan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.Address;
import com.jordan.service.AddressService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/setBillingAddress")
	public void setBillingAddress(@RequestBody Address address) {
		addressService.setBillingAddress(address);
	}
	
	@PostMapping("/setShippingAddress")
	public void setShippingAddress(@RequestBody Address address) {
		addressService.setShippingAddress(address);
	}
	
	@PostMapping("/setAddress")
	public void setAddress(@RequestBody Address address) {
		addressService.setShippingAddress(address);
		addressService.setBillingAddress(address);
	}
	
	@GetMapping("/getAddresses")
	public List<Address> getAddresses(Principal user){
		return addressService.getAddressesByUsername(user.getName());
	}
	
	@PostMapping("/delete")
	public void deleteAddress(@RequestBody Address address) {
		addressService.deleteAddress(address);
		
	}
	
	@PostMapping("/addAddress")
	public void addAddress(@RequestBody Address address, Principal user) {
		addressService.addAddress(address, user.getName());
	}

}
