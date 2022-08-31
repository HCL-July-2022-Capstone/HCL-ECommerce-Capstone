//package com.jordan.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.jordan.model.Product;
//
//import java.util.List;
//
//public interface ProductRepository extends JpaRepository<Product, Integer>{
//
////	@Modifying
////	@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
////	void setUserInfoById(String firstname, String lastname, Integer userId);
//
//    //shefa
//    //all products
//    List<Product> findAll();
//    //find
//    Product findByProductId(Integer id);
//}