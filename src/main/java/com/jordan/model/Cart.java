package com.jordan.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Cart {
	private List<Product> products = new ArrayList<>();
	
	public void addToCart(Product product) {
		this.products.add(product);
	}
	
	public void removeFromCart(Product product) {
		this.products.remove(product);
	}

}
