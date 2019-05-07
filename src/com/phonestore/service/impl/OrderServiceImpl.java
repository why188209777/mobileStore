package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.OrderDao;
import com.phonestore.dao.impl.OrderDaoImpl;
import com.phonestore.entity.Order;
import com.phonestore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao dao = new OrderDaoImpl();
	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return dao.addOrder(order);
	}

	@Override
	public int delOrder(int id) {
		// TODO Auto-generated method stub
		return dao.delOrder(id);
	}

	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return dao.updateOrder(order);
	}

	@Override
	public Order searchOrder(int id) {
		// TODO Auto-generated method stub
		return dao.searchOrder(id);
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
