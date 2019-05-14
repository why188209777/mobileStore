package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.AddressDao;
import com.phonestore.dao.impl.AddressDaoImpl;
import com.phonestore.entity.Address;
import com.phonestore.service.AddressService;

public class AddressServiceImpl implements AddressService{

	AddressDao ad = new AddressDaoImpl();
	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		return ad.addAddress(address);
	}

	@Override
	public int delAddress(int id) {
		// TODO Auto-generated method stub
		return ad.delAddress(id);
	}

	@Override
	public int updateAddress(Address address) {
		// TODO Auto-generated method stub
		return ad.updateAddress(address);
	}

	@Override
	public Address searchAddress(int id) {
		// TODO Auto-generated method stub
		return ad.searchAddress(id);
	}

	@Override
	public List<Address> getAll() {
		// TODO Auto-generated method stub
		return ad.getAll();
	}

	@Override
	public List<Address> getAllAddressByPage(int pageIndex, int pageSize, Object... params) {
		// TODO Auto-generated method stub
		return ad.getAllAddressByPage(pageIndex, pageSize, params);
	}

	@Override
	public int getTotalCount(Object... params) {
		// TODO Auto-generated method stub
		return ad.getTotalCount(params);
	}
	
}
