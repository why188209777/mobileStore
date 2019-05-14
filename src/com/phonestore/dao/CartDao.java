package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Cart;


public interface CartDao {
	public int addCart(Cart cart);
	public int delCart(int id);
	public int updateCart(Cart cart);
	public Cart searchCart(int id);
	public List<Cart> getAllCart(int userId);
	//获取checked的信息用于结算订单
	public List<Cart> getAllCartChecked(int userId);
}
