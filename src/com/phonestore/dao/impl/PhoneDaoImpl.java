package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.entity.Phone;
import com.phonestore.util.DBUtil;
import com.phonestrore.dao.PhoneDao;

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
				phone.getPhoneId(), phone.getName(), phone.getBrand(),
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
				phone.getPhoneId(), phone.getName(), phone.getBrand(),
				phone.getPrice(), phone.getNum(), phone.getImage(),
				phone.getSize(), phone.getColor(), phone.getRam(),
				phone.getRom(), phone.getNetType(), phone.getCamera(),
				phone.getCpu(), phone.getOperatingSystem(), phone.getDescription(), phone.getId()
				);
	}

	@Override
	public Phone searchPhone(int id) {
		String sql = "select * from phone where id=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, id);
	}

	@Override
	public List<Phone> getAll() {
		String sql = "select * from phone";
		return DBUtil.getObjects(Phone.class, sql);
	}

	
}
