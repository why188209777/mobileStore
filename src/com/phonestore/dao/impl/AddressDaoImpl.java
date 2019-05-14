package com.phonestore.dao.impl;


import java.util.List;

import com.phonestore.dao.AddressDao;
import com.phonestore.entity.Address;
import com.phonestore.util.DBUtil;

public class AddressDaoImpl implements AddressDao{

	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub

		String sql="insert into address (name, city, detail, postalcode, phonenum, userid) values (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql,
			address.getName(), address.getCity(), address.getDetail(),
			address.getPostalcode(), address.getPhoneNum(), address.getUserId());
	}

	@Override
	public int delAddress(int id) {
		// TODO Auto-generated method stub
		String sql="delete from address where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int updateAddress(Address address) {
		// TODO Auto-generated method stub
		String sql="update address set name=?, city=?, detail=?, postalcode=?, phonenum=?, userid=? where id=?";
		return DBUtil.executeUpdate(sql,
			address.getName(), address.getCity(), address.getDetail(),
			address.getPostalcode(), address.getPhoneNum(),
			address.getUserId(),address.getId());
	}

	@Override
	public Address searchAddress(int id) {
		// TODO Auto-generated method stub
		String sql="select * from address where id=?";
		Address address = (Address) DBUtil.getObject(Address.class, sql, id);
		return address;
	}

	@Override
	public List<Address> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from address";
		List<Address> list = DBUtil.getObjects(Address.class, sql);
		return list;
	}

	@Override
	public List<Address> searchAllAddressByUserId(int userId) {
		String sql="select * from address where userid=?";
		return DBUtil.getObjects(Address.class, sql, userId);
	}
	
}
