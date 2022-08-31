package com.jordan.model;


import java.util.Set;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity

@Table(name="roles")
@Getter
@Setter

public class Roles {
	@Id @GeneratedValue
	@Column(name = "role_id")
	private int roleId;
	@Column
	private String roleName;

}
