package com.phonestore.dao;

import java.util.List;

import com.phonestore.entity.Image;

public interface ImageDao {
	public int addImage(Image image);
	public int updateImage(Image image);
	public int delImage(int id);
	public List<Image> getlist();
}
