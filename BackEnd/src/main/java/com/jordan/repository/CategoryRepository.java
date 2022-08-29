package com.jordan.repository;

import com.jordan.model.ProductCategory;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<ProductCategory, ID> {

    ProductCategory findById(int id);

    List<ProductCategory> findAll();

    void deleteById(int id); //
}
