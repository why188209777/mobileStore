package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Address;

public interface AddressService {
	public int addAddress(Address address);
	public int delUser(int id);
	public int updateUser(Address address);
	//
	public Address searchUser(int id);
	public List<Address> getAll();
}
