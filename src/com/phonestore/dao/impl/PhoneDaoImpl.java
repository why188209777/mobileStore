package com.phonestore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.phonestore.dao.PhoneDao;
import com.phonestore.entity.Phone;
import com.phonestore.service.impl.PhoneServiceImpl;
import com.phonestore.util.DBUtil;

public class PhoneDaoImpl implements PhoneDao {

	@Override
	public int addPhone(Phone phone) {
		String sql = "insert into phone "
				+ "(phoneid, phonename, brand, price,"
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
				+ "phoneid=?, phonename=?, brand=?, price=?,"
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
		String sql = "select * from phone where id=?";
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
	public Phone searchPhone(String imageUrl) {
		String sql="select * from phone where image=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, imageUrl);
	}
	
	//根据品牌
	@Override
	public List<Phone> searchBrandPhone(String brand,int pageIndex, int pageSize) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql="select * from phone where brand=? limit ?,?";
		return DBUtil.getObjects(Phone.class, sql, brand,pageIndex,pageSize);
	}

	@Override
	public List<Phone> searchPricePhone(String minPrice, String maxPrice,int pageIndex, int pageSize) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql="SELECT * from phone WHERE price>=? AND price<=? limit ?,?";
		return DBUtil.getObjects(Phone.class, sql, minPrice,maxPrice,pageIndex,pageSize);
	}

	@Override
	public List<Phone> searchDistinctBrand() {
		String sql="SELECT DISTINCT brand from phone";
		return DBUtil.getObjects(Phone.class, sql);
	}

	@Override
	public List<Phone> vagueSearchPhone(String keyword,int pageIndex, int pageSize) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql="SELECT * FROM phone where concat(phoneid, brand,phonename) like \"%\"?\"%\" limit ?,?";
		return DBUtil.getObjects(Phone.class, sql, keyword,pageIndex,pageSize);
	}
	
	
	//以下查询数据量
	@Override
	public long getTotalCountByBrand(String brand) {
		String sql="select count(*) from phone where brand=?";
		return DBUtil.getTotalCount(sql, brand);
	}

	@Override
	public long getTotalCountByVague(String keyword) {
		String sql="SELECT count(*) FROM phone WHERE concat(phoneid, brand,phonename) like \"%\"?\"%\"";
		return DBUtil.getTotalCount(sql, keyword);
	}

	@Override
	public long getTotalCountByPrice(String minPrice,String maxPrice) {
		String sql="SELECT count(*) from phone WHERE price>=? AND price<=?";
		return DBUtil.getTotalCount(sql, minPrice,maxPrice);
	}

	@Override
	public Phone searchPhoneByPhoneId(String phoneid) {
		String sql="select * from phone where phoneid=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, phoneid);
	}

	@Override
	public int delPhoneByPhoneId(String phoneid) {
		String sql = "delete from phone where phoneid=?";
		return DBUtil.executeUpdate(sql, phoneid);
	}

	@Override
	public int updatePhoneByNum(int num, String phoneid) {
		String sql="UPDATE phone set num=? WHERE phoneid=?";
		return DBUtil.executeUpdate(sql,num,phoneid);
	}

	@Override
	public int modifyPhone(Phone phone) {
		String sql="update phone set "
				+ "price=?,"
				+ "num=?,"
				+ "description=? where phoneid=?";
		return DBUtil.executeUpdate(sql, phone.getPrice(),phone.getNum(),phone.getDescription(),phone.getPhoneId());

	}

	@Override
	public Phone getPhoneByPhoneId(int phoneId) {
		String sql="select * from phone where phoneid=?";
		return (Phone) DBUtil.getObject(Phone.class, sql, phoneId);
	}
}
