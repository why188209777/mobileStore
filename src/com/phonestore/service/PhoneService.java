package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Phone;

public interface PhoneService {

	public int addPhone(Phone phone);
	public int delPhone(int id);
	public int updatePhone(Phone phone);
	public Phone searchPhone(int id);
	public List<Phone> getAll();
}
