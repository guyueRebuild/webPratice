package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.PurchasingDao;
import com.iTeam.model.Purchasing;
import com.iTeam.service.PurchasingService;

@Service("PurchasingService")
public class PurchasingServiceImpl implements PurchasingService {

	@Resource
	private PurchasingDao purchasingDao;
	
	@Override
	public List<Purchasing> getPurchasingList(Map<String, Object> map) {
		return purchasingDao.getPurchasingList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return purchasingDao.getTotal(map);
	}

	@Override
	public int add(Purchasing purchasing) {
		return purchasingDao.add(purchasing);
	}

	@Override
	public int update(Purchasing purchasing) {
		return purchasingDao.update(purchasing);
	}

	@Override
	public int delete(Integer id) {
		return purchasingDao.delete(id);
	}

}
