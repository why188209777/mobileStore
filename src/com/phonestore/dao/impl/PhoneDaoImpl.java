package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.PhoneDao;
import com.phonestore.entity.Phone;
import com.phonestore.util.DBUtil;

public class PhoneDaoImpl implements PhoneDao {

	@Override
	public int addPhone(Phone phone) {
		String sql = "insert into phone "
				+ "(phoneid, name, brand, price,"
				+ "num, image, size, color, ram,"
				+ "rom, nettype, camera, cpu,"
				+ "operatingsystem, description) values "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql,
				phone.getPhoneId(), phone.getPhonename(), phone.getBrand(),
				phone.getPrice(), phone.getNum(), phone.getImage(),
				phone.getSize(), phone.getColor(), phone.getRam(),
				phone.getRom(), phone.getNetType(), phone.getCamera(),
				phone.getCpu(), phone.getOperatingSystem(), phone.getDescription()
				);
	}

	@Override
	public int delPhone(int id) {
		String sql = "delete from phone where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public int updatePhone(Phone phone) {
		String sql = "update phone set "
				+ "phoneid=?, name=?, brand=?, price=?,"
				+ "num=?, image=?, size=?, color=?, ram=?,"
				+ "rom=?, nettype=?, camera=?, cpu=?,"
				+ "operatingsystem=?, description=? where id=?";
		return DBUtil.executeUpdate(sql,
				phone.getPhoneId(), phone.getPhonename(), phone.getBrand(),
				phone.getPrice(), phone.getNum(), phone.getImage(),
				phone.getSize(), phone.getColor(), phone.getRam(),
				phone.getRom(), phone.getNetType(), phone.getCamera(),
				phone.getCpu(), phone.getOperatingSystem(), phone.getDescription(), phone.getId()
				);
	}

	@Override
	public Phone searchPhone(int id) {
		String sql = "select * from phone where phoneid=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, id);
	}

	@Override
	public List<Phone> getAll() {
		String sql = "select * from phone";
		return DBUtil.getObjects(Phone.class, sql);
	}

	@Override
	public long getTotalCount() {
		String sql = "select count(*) from phone";
		return DBUtil.getTotalCount(sql);
	}

	//分页查询
	@Override
	public List<Phone> getAllPhoneByPage(int pageIndex, int pageSize) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql = "select * from phone limit ?,?";
		return DBUtil.getObjects(Phone.class, sql, pageIndex, pageSize);
	}

	@Override
	public Phone getPhoneByPhoneId(int phoneId) {
		// TODO Auto-generated method stub
		String sql ="select * from phone where phoneid=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, phoneId);
	}
}
