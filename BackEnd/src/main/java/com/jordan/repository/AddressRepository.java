package com.jordan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jordan.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{
	List<Address> findAllByUsername(String username);
}
