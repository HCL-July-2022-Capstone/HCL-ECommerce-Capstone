package com.jordan.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="orders")
public class Orders {
	@Id 
	@GeneratedValue
	private int orderId;
//	private int productId;
	private String orderNumber;
	private float totalPrice;
	private int totalQuantity;
//	//private int userId;
//	private int addressId;
	private String orderStatus;
	
	// one to many unidirectional mapping
    // default fetch type for OneToMany: LAZY
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderId")
    private List<Product> product;
	
    
	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
	@JsonIgnore
	private User userOrder;
	
}
