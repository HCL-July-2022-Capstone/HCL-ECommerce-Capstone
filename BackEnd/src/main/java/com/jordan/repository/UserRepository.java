package com.jordan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.jordan.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
