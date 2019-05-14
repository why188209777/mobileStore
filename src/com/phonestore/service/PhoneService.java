package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Phone;

public interface PhoneService {

	public int addPhone(Phone phone);
	public int delPhone(int id);
	public int updatePhone(Phone phone);
	public int modifyPhone(Phone phone);//修改手机配置
	public int updatePhoneByNum(int num,String phoneid);//根据ID改变库存
	public Phone searchPhone(int id);
	public Phone searchPhoneByPhoneId(String phoneid);//通过phoneid去查找删除
	public int delPhoneByPhoneId(String phoneid);
	public Phone searchPhone(String imageUrl);//根据图片地址查询图片对象
	public List<Phone> getAll();
	public long getTotalCount();
	public long getTotalCountByPrice(String minPrice,String maxPrice);//根据品牌价格
	public long getTotalCountByBrand(String brand);//根据品牌数据量
	public long getTotalCountByVague(String phoneName);//模糊查询数据量
	public List<Phone> getAllPhoneByPage(int pageIndex, int pageSize);
	public List<Phone> searchBrandPhone(String brand,int pageIndex, int pageSize);//根据手机品牌查询手机
	public List<Phone> searchPricePhone(String minPrice,String maxPrice,int pageIndex, int pageSize);//根据价格区间查询
	public List<Phone> vagueSearchPhone(String phoneName,int pageIndex, int pageSize);//模糊查询
	public List<Phone> searchDistinctBrand();//查询去重的品牌
}
