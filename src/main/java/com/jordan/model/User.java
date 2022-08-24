package com.jordan.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String role;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	Set<Roles> roles;

	@OneToMany(mappedBy = "users")
	private Set<Address> address;

	@OneToMany(mappedBy = "userOrder")
	private Set<Orders> order;

}
