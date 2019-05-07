package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Cart;

public interface CartService {
	public int addCart(Cart cart);
	public int delUser(int id);
	public int updateUser(Cart cart);
	//
	public Cart searchUser(int id);
	public List<Cart> getAll();
}
