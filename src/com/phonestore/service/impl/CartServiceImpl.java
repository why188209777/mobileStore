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
	public int updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return cd.updateCart(cart);
	}

	@Override
	public Cart searchCart(int id) {
		// TODO Auto-generated method stub
		return cd.searchCart(id);
	}

	@Override
	public List<Cart> getAll() {
		// TODO Auto-generated method stub
		return cd.getAll();
	}


	@Override
	public int delCart(int id) {
		// TODO Auto-generated method stub
		return cd.delCart(id);
	}


	@Override
	public List<Cart> getAllCartChecked(int userId) {
		// TODO Auto-generated method stub
		return cd.getAllCartChecked(userId);
	}


	@Override
	public int updateNum(int id, int num) {
		// TODO Auto-generated method stub
		return cd.updateNum(id, num);
	}


	@Override
	public int updateChecked(int id, int checked) {
		// TODO Auto-generated method stub
		return cd.updateChecked(id, checked);
	}


	@Override
	public int delAllCart() {
		// TODO Auto-generated method stub
		return cd.delAllCart();
	}


	@Override
	public int updateAllChecked() {
		// TODO Auto-generated method stub
		return cd.updateAllChecked();
	}

}
