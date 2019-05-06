package com.phonestore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.phonestore.entity.User;
import com.phonestore.util.DBUtil;
import com.phonestrore.dao.UserDao;

public class UserDaoImpl implements UserDao{
	

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user VALUES (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, user.getId(),user.getUserName(),user.getPassword(),user.getVip(),user.getAdmin(),user.getPhoneNum());
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		String sql="delete from user where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int updateUser(User user) {
		String sql="update user set username=?,password=?,vip=?,guanliyuan=?,phonenum=? where id=?";
	    return DBUtil.executeUpdate(sql,user.getUserName(),user.getPassword(),user.getVip(),user.getAdmin(),user.getAdmin(),user.getPhoneNum(),user.getId());
	}

	@Override
	public User searchUser(int id) {
		// TODO Auto-generated method stub
		User user=null;
		String sql="select * from user where id=?";
		user=(User) DBUtil.getObject(User.class, sql, id);
		return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		String sql="select * from user";
		List<User> user = DBUtil.getObjects(User.class, sql);
		return user;
	}
}
