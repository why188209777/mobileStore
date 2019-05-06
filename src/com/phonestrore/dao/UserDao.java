package com.phonestrore.dao;

import java.util.List;
import com.phonestore.entity.User;

public interface UserDao {
	public int addUser(User user);
	public int delUser(int sid);
	public int updateUser(User use);
	//
	public User searchUser(int id);
	public List<User> getAll();
}
