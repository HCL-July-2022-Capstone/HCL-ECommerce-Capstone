package com.jordan.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productCategory")
public class ProductCategory {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String categoryName;
	
	@EqualsAndHashCode.Exclude @ToString.Exclude
	 @OneToMany(mappedBy="category", cascade=CascadeType.ALL)
	 @JsonIgnore
	    private Set<Product> product;
	 
	 public void addProduct(List<ProductCategory> productCategory) {
		 productCategory.forEach(prod->prod.setProduct(product));
	 }
}
