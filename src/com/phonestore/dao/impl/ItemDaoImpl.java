package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.ItemDao;
import com.phonestore.entity.Item;
import com.phonestore.util.DBUtil;

public class ItemDaoImpl implements ItemDao {

	@Override
	public int addUItem(Item item) {
		String sql = "insert into item "
				+ "(itemid, name, image, price, number, orderid) "
				+ "values (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql,
				item.getItemId(), item.getName(),
				item.getImage(), item.getPrice(),
				item.getNumber(), item.getOrderId());
	}

	@Override
	public int delItem(int id) {
		String sql = "delete from item where id=?";
		return DBUtil.executeUpdate(sql, id);
	}


	@Override
	public int delItemByOrderId(String orderId) {
		String sql = "delete from item where orderid=?";
		return DBUtil.executeUpdate(sql, orderId);
	}
	
	@Override
	public int updateItem(Item item) {
		String sql = "update item set "
				+ "itemid=?, name=?, image=?, price=?,"
				+ "number=?, orderid=? where id=?";
		return DBUtil.executeUpdate(sql,
				item.getItemId(), item.getName(),
				item.getImage(), item.getPrice(),
				item.getNumber(), item.getOrderId(), item.getId());
	}

	@Override
	public Item searchItem(int id) {
		String sql = "select * from item where id=?";
		return (Item) DBUtil.getObject(Item.class, sql, id);
	}

	@Override
	public List<Item> getAll() {
		String sql = "select * from item";
		return DBUtil.getObjects(Item.class, sql);
	}

	@Override
	public List<Item> searchItemsByOrderId(String orderId) {
		String sql = "select * from item where orderid=?";
		return DBUtil.getObjects(Item.class, sql, orderId);
	}
}
