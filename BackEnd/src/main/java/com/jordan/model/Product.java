package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(schema = "inventory", name = "Products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "ProductId")
    private int productId;
    @Column(name = "CategoryName")
    private String categoryName;
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "ProductPrice")
    private double productPrice;
    @Column(name = "QuantityOnHand")
    private int quantityOnHand;
    @Column(name = "ProductDescription")
    private String productDescription;
    @Column(name = "Image")
    private String image;
    
    	public void decreaseStock(int n) {
		this.quantityOnHand -= n;
	}
	public void decreaseStock() {
		this.quantityOnHand -= 1;
	}


}
