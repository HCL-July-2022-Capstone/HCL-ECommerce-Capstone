package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}