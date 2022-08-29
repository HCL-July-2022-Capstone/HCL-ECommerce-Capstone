package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productCategory", schema = "Inventory")
public class ProductCategory {
	@Id
	@GeneratedValue
	private int id;
	private String categoryName;
}
