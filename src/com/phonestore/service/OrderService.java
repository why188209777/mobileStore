package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Item;
import com.phonestore.entity.Order;

public interface OrderService {

	public int addOrder(Order order);
	public int delOrder(int id);
	public int delOrderByOrderId(String orderId);
	public int updateOrder(Order order);
	public Order searchOrder(int id);
	public List<Order> getAll();
	public List<Order> searcOrderByUserId(int userid);
}
