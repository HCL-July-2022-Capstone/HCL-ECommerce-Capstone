package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.model.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Integer>{

}
