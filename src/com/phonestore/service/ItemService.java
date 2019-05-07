package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Item;

public interface ItemService {

	public int addUItem(Item item);
	public int delItem(int id);
	public int updateItem(Item item);
	public Item searchItem(int id);
	public List<Item> getAll();
}
