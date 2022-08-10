package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.User;
import com.jordan.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping("/users")
	public List<User> listallusers() {
		return service.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getEmpId(@PathVariable Integer id) {
		Optional<User> user = service.getUserById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		service.addUser(user);
	}
}
