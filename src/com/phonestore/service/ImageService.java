package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Image;

public interface ImageService {
	public int addImage(Image image);
	public int updateImage(Image image);
	public int delImage(int id);
	public List<Image> getlist();
}
