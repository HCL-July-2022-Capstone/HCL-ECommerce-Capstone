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
@Table(schema = "inventory", name = "Product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "ProductId")
    private int productId;
    @Column(name = "CategoryName")
    private String categoryName;
    @Column(name = "CategoryId")
    private String categoryId;
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

}
