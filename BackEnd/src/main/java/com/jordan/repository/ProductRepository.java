package com.jordan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jordan.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	public List<Product> findByproductNameContaining(String productName);

	List<Product> findByQuantityOnHandLessThan(int quantityOnHand);

	List<Product> getByCategoryName(String categoryName);
}