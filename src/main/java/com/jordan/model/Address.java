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
@Table(name="Address")
public class Address {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	User user;
	private String street;
	private String unit;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private int phone;
}
