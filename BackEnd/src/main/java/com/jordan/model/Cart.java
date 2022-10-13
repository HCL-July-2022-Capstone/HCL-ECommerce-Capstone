package com.jordan.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "carts")
public class Cart
{
	@Id
	@Column(name = "cart_id")
	private int id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "carts_products", joinColumns = @JoinColumn(name = "cartId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	private List<Product> products = new ArrayList<Product>();

	@Column
	private String username;

	public List<Product> getProducts()
	{
		Logger logger = LoggerFactory.getLogger(Cart.class);
		logger.warn("Getting products from cart");
		return products;
	}

	public void addToCart(Product product)
	{
		this.products.add(product);
	}

	public void removeFromCart(Product product)
	{
		this.products.remove(product);
	}

	public void emptyCart()
	{
		this.products.clear();
	}

	public boolean IsEmpty()
	{
		return this.products.isEmpty();
	}

	public List<Product> getCart()
	{
		return this.products;
	}

	public Float getTotalPrice()
	{
		return products.stream().map(i -> i.getProductPrice()).reduce((float) 0, (a, b) -> a + b);
	}
}