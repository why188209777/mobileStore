package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.PhoneDao;
import com.phonestore.dao.impl.PhoneDaoImpl;
import com.phonestore.entity.Phone;
import com.phonestore.service.PhoneService;

public class PhoneServiceImpl implements PhoneService {

	PhoneDao dao = new PhoneDaoImpl();
	@Override
	public int addPhone(Phone phone) {
		// TODO Auto-generated method stub
		return dao.addPhone(phone);
	}

	@Override
	public int delPhone(int id) {
		// TODO Auto-generated method stub
		return dao.delPhone(id);
	}

	@Override
	public int updatePhone(Phone phone) {
		// TODO Auto-generated method stub
		return dao.updatePhone(phone);
	}

	@Override
	public Phone searchPhone(int id) {
		// TODO Auto-generated method stub
		return dao.searchPhone(id);
	}

	@Override
	public List<Phone> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public long getTotalCount() {
		// TODO Auto-generated method stub
		return dao.getTotalCount();
	}

	@Override
	public List<Phone> getAllPhoneByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getAllPhoneByPage(pageIndex, pageSize);
	}

	@Override
	public Phone searchPhone(String imageUrl) {
		return dao.searchPhone(imageUrl);
	}

	@Override
	public List<Phone> searchBrandPhone(String brand,int pageIndex, int pageSize) {
		return dao.searchBrandPhone(brand,pageIndex,pageSize);
	}

	@Override
	public List<Phone> searchPricePhone(String minPrice, String maxPrice,int pageIndex, int pageSize) {
		return dao.searchPricePhone(minPrice, maxPrice,pageIndex,pageSize);
	}

	@Override
	public List<Phone> searchDistinctBrand() {
		return dao.searchDistinctBrand();
	}

	@Override
	public List<Phone> vagueSearchPhone(String phoneName,int pageIndex, int pageSize) {
		return dao.vagueSearchPhone(phoneName,pageIndex,pageSize);
	}

	@Override
	public long getTotalCountByBrand(String brand) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByBrand(brand);
	}

	@Override
	public long getTotalCountByVague(String phoneName) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByVague(phoneName);
	}

	@Override
	public long getTotalCountByPrice(String minPrice, String maxPrice) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByPrice(minPrice, maxPrice);
	}

	@Override
	public Phone searchPhoneByPhoneId(String phoneid) {
		// TODO Auto-generated method stub
		return dao.searchPhoneByPhoneId(phoneid);
	}

	@Override
	public int delPhoneByPhoneId(String phoneid) {
		// TODO Auto-generated method stub
		return dao.delPhoneByPhoneId(phoneid);
	}

	@Override
	public int updatePhoneByNum(int num, String phoneid) {
		return dao.updatePhoneByNum(num, phoneid);
	}

	@Override
	public int modifyPhone(Phone phone) {
		// TODO Auto-generated method stub
		return dao.modifyPhone(phone);
	}

}
