package com.jordan.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="roles")
public class Roles {
	@Id @GeneratedValue
	private int roleId;
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	Set<User> user;
}
