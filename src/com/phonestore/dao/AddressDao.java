package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Address;
import com.phonestore.entity.Order;

public interface AddressDao {
	public int addAddress(Address address);
	public int delAddress(int id);
	public int updateAddress(Address address);
	public Address searchAddress(int id);
	public List<Address> getAll();
	public List<Address> getAllAddressByPage(int pageIndex, int pageSize, Object...params);
	public int getTotalCount(Object...params);
	public List<Address> searchAllAddressByUserId(int userId);
}
