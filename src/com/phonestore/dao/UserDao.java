package com.phonestore.dao;

import java.util.List;
import com.phonestore.entity.User;

public interface UserDao {
	public int addUser(User user);
	public int delUser(int sid);
	public int updateUser(User user);
	public User searchUser(int id);
	public List<User> getAll();
	public User login(String name, String password);
	public List<User> getAllUserByPage(int pageIndex, int pageSize, Object...params);
	public int getTotalCount(Object...params);
}
