package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Item;

public interface ItemDao {

	public int addUItem(Item item);
	public int delItem(int id);
	public int delItemByOrderId(String orderId);
	public int updateItem(Item item);
	public Item searchItem(int id);
	public List<Item> getAll();
	//根据订单号查询订单详细信息
	public List<Item> searchItemsByOrderId(String orderId);
}
