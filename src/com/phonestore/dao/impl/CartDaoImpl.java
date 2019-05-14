package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.CartDao;
import com.phonestore.entity.Cart;
import com.phonestore.util.DBUtil;

public class CartDaoImpl implements CartDao{

	@Override
	public int addCart(Cart cart) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO cart (phoneid,num,userid,checked) VALUES (?,?,?,?)";
		return DBUtil.executeUpdate(sql, cart.getPhoneId(),cart.getNum(),cart.getUserId(),cart.getChecked());
	}
	
	@Override
	public int delCart(int id) {
		// TODO Auto-generated method stub
		String sql="delete from cart where id=?";
		return DBUtil.executeUpdate(sql, id);
		
	}

	@Override
	public int updateCart(Cart cart) {
		// TODO Auto-generated method stub
		String sql="update address set phoneid=?,num=?,userid=?,checked=? where id=?";
		return DBUtil.executeUpdate(sql, cart.getPhoneId(),cart.getNum(),cart.getUserId(),cart.getChecked(),cart.getId());
	}

	@Override
	public Cart searchCart(int id) {
		// TODO Auto-generated method stub
		String sql="select * from cart where id=?";
		Cart cart = (Cart) DBUtil.getObject(Cart.class, sql, id);
		return cart;
	}

	@Override
	public List<Cart> getAllCart(int userId) {
		// TODO Auto-generated method stub
		String sql="select * from cart where userid=?";
		List<Cart> list = DBUtil.getObjects(Cart.class, sql, userId);
		return list;
	}
	public static void main(String[] args) {
		CartDaoImpl cd = new CartDaoImpl();
//		Cart cart = new Cart(1,2,3);
//		cd.addCart(cart);
//		cd.delUser(5);
//		for(int i=0;i<cd.getAll().size();i++) {
//			System.out.println(cd.getAll().get(i));
//		}
		System.out.println(cd.searchCart(2));
	}

	@Override
	public List<Cart> getAllCartChecked(int userId) {
		// TODO Auto-generated method stub
		String sql = "select * from cart where userid=? and checked=1";
		return DBUtil.getObjects(Cart.class, sql,userId);
	}

}
