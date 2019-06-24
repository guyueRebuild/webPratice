package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.iTeam.dao.SalesReturnDao;
import com.iTeam.model.Sales;
import com.iTeam.model.SalesReturn;
import com.iTeam.service.SalesReturnService;

@Service
@Transactional
public class SalesReturnServiceImpl implements SalesReturnService {
	
	@Autowired
	private SalesReturnDao srDao;

	@Override
	public List<SalesReturn> findAll(Map<String, Object> map) {
		
		
		return srDao.findAll(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {

		return srDao.getTotal(map);
	}

	@Override
	public Sales findSalesBysNo(int sNo) {

		return srDao.findSalesBysNo(sNo);
	}

	@Override
	public int add(SalesReturn sr) {

		return srDao.add(sr);
	}

	@Override
	public int update(SalesReturn sr) {

		return srDao.update(sr);
	}

	@Override
	public void deleteBysNo(int sNo) {
		srDao.deleteBysNo(sNo);

	}

}
