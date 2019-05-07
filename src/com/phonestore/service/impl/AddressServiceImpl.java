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
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return ad.delUser(id);
	}

	@Override
	public int updateUser(Address address) {
		// TODO Auto-generated method stub
		return ad.updateUser(address);
	}

	@Override
	public Address searchUser(int id) {
		// TODO Auto-generated method stub
		return ad.searchUser(id);
	}

	@Override
	public List<Address> getAll() {
		// TODO Auto-generated method stub
		return ad.getAll();
	}
	public static void main(String[] args) {
		AddressService as = new AddressServiceImpl();
		List<Address> adr = as.getAll();
		for (int i=0;i<adr.size();i++) {
			System.out.println(adr.get(i));
		}
		Address address = new Address("浙江省温州市", 1);
		as.addAddress(address);
	}

}
