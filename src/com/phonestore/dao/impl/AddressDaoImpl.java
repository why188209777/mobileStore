package com.phonestore.dao.impl;


import java.util.List;

import com.phonestore.entity.Address;
import com.phonestore.util.DBUtil;
import com.phonestrore.dao.AddressDao;

public class AddressDaoImpl implements AddressDao{

	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		String sql="insert into address (name,userid) values (?,?)";
		return DBUtil.executeUpdate(sql, address.getName(),address.getUserId());
	}

	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		String sql="delete from address where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int updateUser(Address address) {
		// TODO Auto-generated method stub
		String sql="update address set name=?,userid=? where id=?";
		return DBUtil.executeUpdate(sql, address.getName(),address.getUserId(),address.getId());
	}

	@Override
	public Address searchUser(int id) {
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
	
}
