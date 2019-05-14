package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.ImageDao;
import com.phonestore.dao.impl.ImageDaoImpl;
import com.phonestore.entity.Image;
import com.phonestore.service.ImageService;

public class ImageServiceImpl implements ImageService{
	ImageDao idi = new ImageDaoImpl();
	
	@Override
	public int addImage(Image image) {
		// TODO Auto-generated method stub
		return idi.addImage(image);
	}

	@Override
	public int updateImage(Image image) {
		// TODO Auto-generated method stub
		return idi.updateImage(image);
	}

	@Override
	public int delImage(int id) {
		// TODO Auto-generated method stub
		return idi.delImage(id);
	}

	@Override
	public List<Image> getlist() {
		// TODO Auto-generated method stub
		return idi.getlist();
	}

}
