package com.jordan.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productCategory")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String categoryName;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "category")
	private Set<Product> product;

	public void addProduct(Product product) {
		this.product.add(product);
	}
}
