package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.User;
import com.jordan.repository.UserRepository;
import com.jordan.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	@GetMapping("/")
//	public String Home() {
//		return "Home";
//	}
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.setRole("USER");
		String encryptedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		repo.save(user);
		return "Hi " + user.getFirstName() + " Welcome to Group!";
	}
	
//	
//	@GetMapping("/users")
//	public List<User> listallusers() {
//		return service.getAllUsers();
//	}
//
//	@GetMapping("/users/{id}")
//	public ResponseEntity<User> getEmpId(@PathVariable Integer id) {
//		Optional<User> user = service.getUserById(id);
//		return new ResponseEntity<User>(HttpStatus.OK);
//	}
//	
//	@PostMapping("/users")
//	public void addUser(@RequestBody User user) {
//		service.addUser(user);
//	}
}
