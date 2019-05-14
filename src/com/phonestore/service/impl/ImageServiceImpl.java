package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.ImageDao;
import com.phonestore.dao.impl.ImageDaoImpl;
import com.phonestore.entity.Image;
import com.phonestore.service.ImageService;

public class ImageServiceImpl implements ImageService{
	ImageDao dao = new ImageDaoImpl();
	
	@Override
	public int addImage(Image image) {
		// TODO Auto-generated method stub
		return dao.addImage(image);
	}

	@Override
	public int updateImage(Image image) {
		// TODO Auto-generated method stub
		return dao.updateImage(image);
	}

	@Override
	public int delImage(int id) {
		// TODO Auto-generated method stub
		return dao.delImage(id);
	}
	

	@Override
	public Image searchImage(int id) {
		// TODO Auto-generated method stub
		return dao.searchImage(id);
	}
	
	@Override
	public List<Image> getlist() {
		// TODO Auto-generated method stub
		return dao.getlist();
	}

	@Override
	public List<Image> getAllImageByPage(int pageIndex, int pageSize, Object... params) {
		// TODO Auto-generated method stub
		return dao.getAllImageByPage(pageIndex, pageSize, params);
	}

	@Override
	public int getTotalCount(Object... params) {
		// TODO Auto-generated method stub
		return dao.getTotalCount(params);
	}


}
