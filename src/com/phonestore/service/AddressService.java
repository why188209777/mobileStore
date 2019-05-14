package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Address;

public interface AddressService {
	public int addAddress(Address address);
	public int delAddress(int id);
	public int updateAddress(Address address);
	public Address searchAddress(int id);
	public List<Address> getAll();
	public List<Address> searchAllAddressByUserId(int userId);
}
