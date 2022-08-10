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
@Table(name="Orders")
public class Orders {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	private int orderNumber;
	private int totalPrice;
	private int totalQuantity;
	private String orderStatus;
	User user;
	Product products;
	
	
}
