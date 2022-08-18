package com.jordan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Orders {
	@Id 
	@GeneratedValue
	private int orderId;
	private int productId;
//	private String orderNumber;
	private float totalPrice;
	private int totalQuantity;
//	//private int userId;
//	private int addressId;
	private String orderStatus;
	
	// one to many unidirectional mapping
    // default fetch type for OneToMany: LAZY
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "orderId")
    private List<Product> product;
	
	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
	private User userOrder;
	
	@ManyToOne
    @JoinColumn(name="addressId", nullable=false)
	private Address address;
}
