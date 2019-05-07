package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.impl.UserDaoImpl;
import com.phonestore.entity.User;
import com.phonestore.service.UserService;
import com.phonestrore.dao.UserDao;

public class UserServiceImpl implements UserService{
	UserDao ud = new UserDaoImpl();
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return ud.addUser(user);
	}

	@Override
	public int delUser(int sid) {
		// TODO Auto-generated method stub
		return ud.delUser(sid);
	}

	@Override
	public int updateUser(User use) {
		// TODO Auto-generated method stub
		return ud.updateUser(use);
	}

	@Override
	public User searchUser(int id) {
		// TODO Auto-generated method stub
		return ud.searchUser(id);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return ud.getAll();
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return ud.login(name, password);
	}

}
