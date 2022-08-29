//package com.jordan.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="product", schema = "inventory")
//public class Product {
//	@Id @GeneratedValue
//	private int productId;
//	private String productName;
//  private String color;
//	private Float productPrice;
//	private int quantityOnHand;
//

//	@ManyToOne
//    @JoinColumn(name="categoryId", referencedColumnName = "id")
//	@JsonIgnore
//	private ProductCategory category;
//
//}
