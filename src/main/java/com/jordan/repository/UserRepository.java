package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
