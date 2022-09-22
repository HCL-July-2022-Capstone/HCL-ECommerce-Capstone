package com.jordan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.model.Orders;

public interface OrdersRepository extends JpaRepository <Orders, Integer>{

}
