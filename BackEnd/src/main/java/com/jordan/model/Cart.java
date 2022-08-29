package com.jordan.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.jordan.model.Product;
import com.jordan.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id") 
	private int id;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "carts_products",
	joinColumns = @JoinColumn(name = "cart_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> products = new ArrayList<>();
	
	@OneToOne(mappedBy = "cart")
	private User user;
	
	
	public void addToCart(Product product) {
		this.products.add(product);
	}
	
	public void removeFromCart(Product product) {
		this.products.remove(product);
	}
	
	public void emptyCart() {
		this.products.clear();
	}
	
	public boolean IsEmpty() {
		return this.products.isEmpty();
	}
	
	public List<Product> getCart() {
		return this.products;
	}
	
	public Float getTotalPrice() {
		return products.stream().map(i -> i.getProductPrice()).reduce((float)0, (a,b) -> a+b);
	}
	
	public User getUser() {
		return user;
	}

}
