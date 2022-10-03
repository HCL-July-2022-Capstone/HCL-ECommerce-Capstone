package com.jordan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.model.Orders;
import com.jordan.repository.OrdersRepository;

@Service
public class OrdersService
{
	@Autowired
	private OrdersRepository repo;

	public List<Orders> getOrders()
	{
		return repo.findAll();
	}

	public Optional<Orders> getOrderById(int id)
	{
		return repo.findById(id);
	}

	public void deleteOrder(int id)
	{
		repo.deleteById(id);
	}

	public void save(Orders order)
	{
		repo.save(order);
	}
}