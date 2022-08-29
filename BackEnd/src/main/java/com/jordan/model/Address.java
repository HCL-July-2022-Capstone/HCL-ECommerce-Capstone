package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address", schema = "inventory")
public class Address {
	@Id 
	@GeneratedValue
	private int addressId;
	private String street;
	private String unit;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
//	@OneToOne
//    @PrimaryKeyJoinColumn
//    private Orders order;
		
}
