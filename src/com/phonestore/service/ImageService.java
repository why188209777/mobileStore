package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Image;

public interface ImageService {
	public int addImage(Image image);
	public int updateImage(Image image);
	public int delImage(int id);
	public Image searchImage(int id);
	public List<Image> getlist();
	public List<Image> getAllImageByPage(int pageIndex, int pageSize, Object...params);
	public int getTotalCount(Object...params);
}
