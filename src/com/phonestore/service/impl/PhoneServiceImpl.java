package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.impl.PhoneDaoImpl;
import com.phonestore.entity.Phone;
import com.phonestore.service.PhoneService;
import com.phonestrore.dao.PhoneDao;

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

}
