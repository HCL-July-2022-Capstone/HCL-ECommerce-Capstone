package com.jordan.repository;

import com.jordan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        //all products
        List<Product> findAll();

        Optional<Product> findByProductName(String productName);
        Optional<Product> searchByProductName(String productName);
}
