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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="products")
@Getter
@Setter

public class Product {
	@Id @GeneratedValue
	private int productId;
	private String productName;
	private String productDescription;
	private Float productPrice;
	private int quantityOnHand;
	private String categoryName;
	private String image;
		
	public void decreaseStock(int n) {
		this.quantityOnHand -= n;
	}
	public void decreaseStock() {
		this.quantityOnHand -= 1;
	}
	
}
