package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Phone;

public interface PhoneDao {

	public int addPhone(Phone phone);
	public int delPhone(int id);
	public int updatePhone(Phone phone);
	public Phone searchPhone(int id);
	public List<Phone> getAll();
	public long getTotalCount();
	public List<Phone> getAllPhoneByPage(int pageIndex, int pageSize);
	public Phone getPhoneByPhoneId(int phoneId);
}
