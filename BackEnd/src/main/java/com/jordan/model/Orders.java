package com.jordan.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders
{
	@Id
	@GeneratedValue
	@Column
	private int orderId;
	private float totalPrice;
	private String orderStatus;
	@Column
	private String username;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	private List<Product> products = new ArrayList<Product>();

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "shippingAddressId", referencedColumnName = "addressId")
	private Address shippingAddress;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "billingAddressId", referencedColumnName = "addressId")
	private Address billingAddress;
	//
	// @ManyToOne
	// @JoinColumn(name="user_id")
	// @JsonBackReference
	// private User user;

	public void setProducts(List<Product> products)
	{
		Logger logger = LoggerFactory.getLogger(Orders.class);
		this.products = products;
		logger.warn("Set Products");
	}

	public void addProduct(Product product)
	{
		products.add(product);
	}

	public void setUser(String username)
	{
		this.username = username;
	}
}