package com.phonestore.dao.impl;


import java.util.List;

import com.phonestore.dao.UserDao;
import com.phonestore.entity.User;
import com.phonestore.util.DBUtil;

public class UserDaoImpl implements UserDao{
	

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user (username,password,vip,admin,phonenum) VALUES (?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, user.getUserName(),user.getPassword(),user.getVip(),user.getAdmin(),user.getPhoneNum());
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		String sql="delete from user where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int updateUser(User user) {
		String sql="update user set username=?,password=?,vip=?,admin=?,phonenum=? where id=?";
	    return DBUtil.executeUpdate(sql,user.getUserName(),user.getPassword(),user.getVip(),user.getAdmin(),user.getPhoneNum(),user.getId());
	}

	@Override
	public User searchUser(int id) {
		// TODO Auto-generated method stub
		String sql="select * from user where id=?";
		User user=(User) DBUtil.getObject(User.class, sql, id);
		return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from user";
		List<User> user = DBUtil.getObjects(User.class, sql);
		return user;
	}
	
	@Override
	public User login(String name, String password) {
		String sql = "select * from user where username=? and password=?";
		return (User) DBUtil.getObject(User.class, sql, name, password);
	}
	

	//模糊分页查询
	@Override
	public List<User> getAllUserByPage(int pageIndex, int pageSize, Object... params) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql = "select * from user where 1=1";
		if (params.length > 0) {
			String key = (String) params[0];
			sql += " and concat(username, phonenum) like \"%\"?\"%\" limit ?,?";
			return DBUtil.getObjects(User.class, sql, key, pageIndex, pageSize);
		}else {
			sql += " limit ?,?";
			return DBUtil.getObjects(User.class, sql, pageIndex, pageSize);
		}
		
	}
	
	
	@Override
	public int getTotalCount(Object...params) {
		String sql = "select count(*) from user where 1=1";
		if (params.length > 0) {
			sql += " and concat(username, phonenum) like \"%\"?\"%\"";
			return (int) DBUtil.getTotalCount(sql, params);
		}else {
			return (int) DBUtil.getTotalCount(sql);
		}
	}
	
}
