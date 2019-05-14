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
	public List<Cart> getAllCart(int userId) {
		// TODO Auto-generated method stub
		return cd.getAllCart(userId);
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

}
