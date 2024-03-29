package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.ItemDao;
import com.phonestore.dao.impl.ItemDaoImpl;
import com.phonestore.entity.Item;
import com.phonestore.service.ItemService;

public class ItemServiceImpl implements ItemService {

	ItemDao dao = new ItemDaoImpl();
	@Override
	public int addUItem(Item item) {
		// TODO Auto-generated method stub
		return dao.addUItem(item);
	}

	@Override
	public int delItem(int id) {
		// TODO Auto-generated method stub
		return dao.delItem(id);
	}

	@Override

	public int delItemByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return dao.delItemByOrderId(orderId);
	}
	
	@Override
	public int updateItem(Item item) {
		// TODO Auto-generated method stub
		return dao.updateItem(item);
	}

	@Override
	public Item searchItem(int id) {
		// TODO Auto-generated method stub
		return dao.searchItem(id);
	}

	@Override
	public List<Item> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public List<Item> searchItemsByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return dao.searchItemsByOrderId(orderId);
	}



}
