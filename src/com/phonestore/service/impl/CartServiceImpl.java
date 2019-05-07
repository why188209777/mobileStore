package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.CartDao;
import com.phonestore.dao.impl.CartDaoImpl;
import com.phonestore.entity.Cart;
import com.phonestore.service.CartService;

public class CartServiceImpl implements CartService{
	CartDao cd = new CartDaoImpl();
	@Override
	public int addCart(Cart cart) {
		// TODO Auto-generated method stub
		return cd.addCart(cart);
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return cd.delUser(id);
	}

	@Override
	public int updateUser(Cart cart) {
		// TODO Auto-generated method stub
		return cd.updateUser(cart);
	}

	@Override
	public Cart searchUser(int id) {
		// TODO Auto-generated method stub
		return cd.searchUser(id);
	}

	@Override
	public List<Cart> getAll() {
		// TODO Auto-generated method stub
		return getAll();
	}

}
