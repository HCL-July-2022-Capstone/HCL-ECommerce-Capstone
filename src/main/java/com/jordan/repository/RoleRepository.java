package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jordan.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{

}
