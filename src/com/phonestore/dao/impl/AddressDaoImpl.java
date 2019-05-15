package com.phonestore.dao.impl;


import java.util.List;

import com.phonestore.dao.AddressDao;
import com.phonestore.entity.Address;
import com.phonestore.entity.Order;
import com.phonestore.util.DBUtil;

public class AddressDaoImpl implements AddressDao{

	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub

		String sql="insert into address (name,city,detail,postalcode,phonenum,userid) values (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, address.getName(),address.getCity(),address.getDetail(),address.getPostalcode(),address.getPhoneNum(),address.getUserId());

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

		String sql="update address set name=?,city=?,detail=?,postalcode=?,phonenum=?,userid=? where id=?";
		return DBUtil.executeUpdate(sql, address.getName(),address.getCity(),address.getDetail(),address.getPostalcode(),address.getPhoneNum(),address.getUserId(),address.getId());

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
	public List<Address> getAllAddressByPage(int pageIndex, int pageSize, Object... params) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql = "select * from address where 1=1";
		if (params.length > 0) {
			String key = (String) params[0];
			sql += " and concat(name,city,detail,phonenum) like \"%\"?\"%\" limit ?,?";
			return DBUtil.getObjects(Address.class, sql, key, pageIndex, pageSize);
		}else {
			sql += " limit ?,?";
			return DBUtil.getObjects(Address.class, sql, pageIndex, pageSize);
		}
	}

	@Override
	public int getTotalCount(Object... params) {
		String sql = "select count(*) from address where 1=1";
		if (params.length > 0) {
			sql += " and concat(name,city,detail,phonenum) like \"%\"?\"%\"";
			return (int) DBUtil.getTotalCount(sql, params);
		}else {
			return (int) DBUtil.getTotalCount(sql);
		}
	}
	public List<Address> searchAllAddressByUserId(int userId) {
		String sql="select * from address where userid=?";
		return DBUtil.getObjects(Address.class, sql, userId);
	}
	
}
