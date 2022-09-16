package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jordan.model.Roles;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{

}
