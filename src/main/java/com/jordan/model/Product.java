package com.jordan.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
	@Id @GeneratedValue
	private int productId;
	private String productName;
	private String color;
	private Float productPrice;
	private int quantityOnHand;
//	private int categoryId;
		
	@ManyToOne
    @JoinColumn(name="categoryId")
	@JsonIgnore
	private ProductCategory category;
	
}
