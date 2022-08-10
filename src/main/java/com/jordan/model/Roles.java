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
@Table(name="Roles")
public class Roles {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String roleId;
	private String roleName;
}
