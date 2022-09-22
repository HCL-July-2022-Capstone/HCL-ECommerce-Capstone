package com.jordan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
//	@Modifying
//	@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//	void setUserInfoById(String firstname, String lastname, Integer userId);
	public List<Product> findByproductNameContaining(String productName);

	List<Product> getByCategoryName(String categoryName);
}
