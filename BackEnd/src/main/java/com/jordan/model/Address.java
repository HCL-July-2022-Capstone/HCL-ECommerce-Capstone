package com.jordan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Getter
//@Setter
@Table(name = "addresses")
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addressId")
	private int id;

	@Column
	private String street;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String zipcode;

	@Column
	private String country;

	@Column
	private String username;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Orders order;

	public static final Address toCustomAddress(AddressStandardClaim standardAddress)
	{
		Address newAddress = new Address();
		newAddress.setStreet(standardAddress.getStreetAddress());
		newAddress.setCity(standardAddress.getLocality());
		newAddress.setState(standardAddress.getRegion());
		newAddress.setZipcode(standardAddress.getPostalCode());
		newAddress.setCountry(standardAddress.getCountry());
		return newAddress;
	}

	public Address(String street, String city, String state, String zipcode, String country, String username)
	{
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.username = username;
	}
}