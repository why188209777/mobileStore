package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.ImageDao;
import com.phonestore.entity.Cart;
import com.phonestore.entity.Image;
import com.phonestore.util.DBUtil;

public class ImageDaoImpl implements ImageDao{

	@Override
	public int addImage(Image image) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO image (imageid,phonename) VALUES (?,?";
		return DBUtil.executeUpdate(sql, image.getImageid(),image.getPhonename());
	}

	@Override
	public int updateImage(Image image) {
		// TODO Auto-generated method stub
		String sql="update image set imageid=?,phonename=? where id=?";
		return DBUtil.executeUpdate(sql, image.getImageid(),image.getPhonename(),image.getId());

	}

	@Override
	public int delImage(int id) {
		// TODO Auto-generated method stub
		String sql="delete from image where id=?";
		return DBUtil.executeUpdate(sql, id);
	}

	@Override
	public List<Image> getlist() {
		// TODO Auto-generated method stub
		String sql="select * from image";
		List<Image> list = DBUtil.getObjects(Image.class, sql);
		return list;
	}

}
