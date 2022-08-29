package com.jordan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="roles", schema = "inventory")
public class Roles {
	@Id @GeneratedValue
	private int roleId;
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	Set<User> user;
}
