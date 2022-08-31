package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name="products")
@Getter
@Setter

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
