package com.jordan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jordan.model.Address;
import com.jordan.model.Cart;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
	 Optional<List<Address>> findAllByUsername(String username);
}
