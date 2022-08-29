package com.jordan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.jordan.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="addresses")
@Getter
@Setter
public class Address {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addressId")
	private int id;
	
	@Column
	private String street;
	
	@Column
	private String unit;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String zipcode;
	
	@Column
	private String country;
	
	@Column(name = "address_phone")
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
		
}
