package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Cart;


public interface CartDao {
	public int addCart(Cart cart);
	public int delCart(int id);
	public int updateCart(Cart cart);
	public int updateNum(int id,int num);
	public int updateChecked(int id,int checked);
	public int delAllCart();
	public int updateAllChecked();
	public Cart searchCart(int id);
	public List<Cart> getAll();
	public List<Cart> getAllCart(int userId);
	//获取checked的信息用于结算订单
	public List<Cart> getAllCartChecked(int userId);
	public int clearChecked();
}
