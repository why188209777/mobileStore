package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Cart;

public interface CartService {
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
	public List<Cart> getAllCartChecked(int userId);
	
}
