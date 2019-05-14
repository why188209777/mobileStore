package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Cart;

public interface CartService {
	public int addCart(Cart cart);
	public int delCart(int id);
	public int updateCart(Cart cart);
	public Cart searchCart(int id);
	public List<Cart> getAllCart(int userId);
	public List<Cart> getAllCartChecked(int userId);
}
