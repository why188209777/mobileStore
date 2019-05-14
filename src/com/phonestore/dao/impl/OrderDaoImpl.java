package com.phonestore.dao.impl;

import java.util.List;
import com.phonestore.dao.OrderDao;
import com.phonestore.entity.Order;
import com.phonestore.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public int addOrder(Order order) {
		String sql = "insert into orders "
				+ "(orderid, userid, createtime, total, address, phonenum, status) values "
				+ "(?,?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, 
				order.getOrderId(), order.getUserId(),
				order.getCreateTime(), order.getTotal(),
				order.getAddress(), order.getPhoneNum(), order.getStatus());
	}

	@Override
	public int delOrder(int id) {
		String sql = "delete from orders where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int delOrderByOrderId(String orderId) {
		String sql = "delete from orders where orderid=?";
		return DBUtil.executeUpdate(sql, orderId);
	}
	
	@Override
	public int updateOrder(Order order) {
		String sql = "update orders set "
				+ "orderid=?, userid=?, createtime=?,"
				+ "total=?, address=?, phonenum=?,"
				+ "status=? where id=?";
		return DBUtil.executeUpdate(sql, 
				order.getOrderId(), order.getUserId(),
				order.getCreateTime(), order.getTotal(),
				order.getAddress(), order.getPhoneNum(),
				order.getStatus(), order.getId());
	}

	@Override
	public Order searchOrder(int id) {
		String sql = "select * from orders where id=?";
		return (Order) DBUtil.getObject(Order.class, sql, id);
	}

	@Override
	public List<Order> getAll() {
		String sql = "select * from orders ";
		return DBUtil.getObjects(Order.class, sql);
	}

	@Override
	//根据用户id查询订单的信息
	public List<Order> searcOrderByUserId(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where userid=?";
		return DBUtil.getObjects(Order.class, sql, userid);
	}


}
