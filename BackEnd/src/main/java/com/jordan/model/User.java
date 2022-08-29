package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "inventory")
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

//	@OneToMany(mappedBy = "userOrder")
//	private Set<Orders> order;
	
	public void addRole(Roles role) {
		this.roles.add(role);
	}

}
