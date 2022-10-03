package com.jordan.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product
{
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private String productDescription;
	private Float productPrice;
	private int quantityOnHand;
	private String categoryName;
	private String image;

	public void decreaseStock(int n)
	{
		this.quantityOnHand -= n;
	}

	public void decreaseStock()
	{
		this.quantityOnHand -= 1;
	}

	@Override
	public String toString()
	{
		return "Product [id =" + productId + ", productName =" + productName + ", productDescription="
				+ productDescription + "," + "productPrice=" + productPrice + ", quantityOnHand=" + quantityOnHand
				+ ", categoryName=" + categoryName + ", image=" + image + "]";
	}
}