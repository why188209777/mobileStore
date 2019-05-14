package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.ImageDao;
import com.phonestore.entity.Image;
import com.phonestore.util.DBUtil;

public class ImageDaoImpl implements ImageDao{

	@Override
	public int addImage(Image image) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO image (imageid,phonename) VALUES (?,?)";
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
	public Image searchImage(int id) {
		// TODO Auto-generated method stub
		String sql="select * from image where id=?";
		Image image = (Image) DBUtil.getObject(Image.class, sql, id);
		return image;
	}
	
	@Override
	public List<Image> getlist() {
		// TODO Auto-generated method stub
		String sql="select * from image";
		List<Image> list = DBUtil.getObjects(Image.class, sql);
		return list;
	}

	@Override
	public List<Image> getAllImageByPage(int pageIndex, int pageSize, Object... params) {
		pageIndex = (pageIndex - 1) * pageSize;
		String sql = "select * from image where 1=1";
		if (params.length > 0) {
			String key = (String) params[0];
			sql += " and concat(imageid, phonename) like \"%\"?\"%\" limit ?,?";
			return DBUtil.getObjects(Image.class, sql, key, pageIndex, pageSize);
		}else {
			sql += " limit ?,?";
			return DBUtil.getObjects(Image.class, sql, pageIndex, pageSize);
		}
	}

	@Override
	public int getTotalCount(Object... params) {
		String sql = "select count(*) from image where 1=1";
		if (params.length > 0) {
			sql += " and concat(imageid, phonename) like \"%\"?\"%\"";
			return (int) DBUtil.getTotalCount(sql, params);
		}else {
			return (int) DBUtil.getTotalCount(sql);
		}
	}


}
