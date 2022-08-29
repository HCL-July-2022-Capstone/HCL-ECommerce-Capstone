package com.jordan.controller;


import java.util.List;
import java.util.Optional;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.common.UserConstants;
import com.jordan.model.Address;
import com.jordan.model.Roles;
import com.jordan.model.User;
import com.jordan.repository.AddressRepository;
import com.jordan.repository.RoleRepository;
import com.jordan.repository.UserRepository;
import com.jordan.service.EmailService;
import com.jordan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AddressRepository addressRepo;
	private RoleRepository roleRepo;

	@Autowired
	private UserService service;


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	Roles role;

	@PostMapping("/join")
	public String add(@RequestBody User user) {				
		String encryptedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		
		repo.save(user);
		emailService.sendRegistrationEmail(user);
		
		return "Hi " + user.getFirstName() + ", Welcome to Group!";
	}
	
	@PostMapping("/addAddress")
	public String addAddress(@RequestBody Address address, @AuthenticationPrincipal User principal) {	
		//this works by getting an incomplete Address (one without a user attached)
		//and attaching a user. idk if thats the right way to do things
		address.setUser(principal);
		addressRepo.save(address);
		return "Added new address to user "+principal.getUsername();
	}
	
	@GetMapping("/getAllUsers")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}
	
	@GetMapping("/get/{id}")
	public Optional<User> getUserId(@PathVariable Integer id) {

		return service.getUserById(id);
	}

	@PutMapping("/update/{id}")
	 public String updateUser(@PathVariable int id, @RequestBody User user) {
		User toUpdate = service.getUserById(id).get();
		if(toUpdate == null) {
			return "User doesn't exist!";
		}
		else {
			if(user.getUsername() != null) toUpdate.setUsername(user.getUsername());
			if(user.getFirstName() != null) toUpdate.setFirstName(user.getFirstName());
			if(user.getLastName()!= null) toUpdate.setLastName(user.getLastName());
			if(user.getPhone()!= null)toUpdate.setPhone(user.getPhone());
			if(user.getPassword()!= null) {
				String encryptedPass = passwordEncoder.encode(user.getPassword());
				toUpdate.setPassword(encryptedPass);
			}
			if(user.getRoles() != null) toUpdate.setRoles(user.getRoles());
			repo.save(toUpdate);
			return "Updated user "+user.getUsername();
		}
		
	 }

	@DeleteMapping("/get/{id}")
	public void deleteUser(@PathVariable Integer id) {

		service.deleteUser(id);
	}

}
