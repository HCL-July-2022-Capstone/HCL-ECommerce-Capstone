package com.jordan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.model.User;
import com.jordan.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public Optional<User> getUserById(int id) {
		return repo.findById(id);
	}

	public void deleteUser(int id) {
		repo.deleteById(id);
	}

	public void addUser(User emp) {
		repo.save(emp);
	}
}
