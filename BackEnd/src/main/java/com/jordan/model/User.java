package com.jordan.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import com.jordan.model.Cart;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jordan.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	@Column
	private int userId;
	@Column(nullable = false)
	private String username;
	@Column
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String phone;
	
	//User is Cart's parent
	@OneToOne(cascade = {CascadeType.ALL})
	private Cart cart;

	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	Set<Roles> roles;


	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Orders> orders;

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Address> addresses = new HashSet<>();
	
	public void addRole(Roles role) {
		this.roles.add(role);
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(Orders order) {
		order.setUser(this);
		this.orders.add(order);
	}
	
	

}
