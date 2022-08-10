package com.jordan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Product")
public class Product {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private String color;
	private int price;
	private int quantity;
	private int quantityLeft;
	ProductCategory productCategory;
}
