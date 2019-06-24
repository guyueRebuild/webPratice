package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.Purchasing_ReturnDao;
import com.iTeam.model.Purchasing_Return;
import com.iTeam.service.Purchasing_ReturnService;

@Service("Purchasing_ReturnService")
public class Purchasing_ReturnServiceImpl implements Purchasing_ReturnService {

	@Resource
	private Purchasing_ReturnDao purchasing_ReturnDao;
	
	@Override
	public List<Purchasing_Return> getPurchasing_ReturnList(Map<String, Object> map) {
		return purchasing_ReturnDao.getPurchasing_ReturnList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return purchasing_ReturnDao.getTotal(map);
	}

	@Override
	public int add(Purchasing_Return purchasing_Return) {
		return purchasing_ReturnDao.add(purchasing_Return);
	}

	@Override
	public int update(Purchasing_Return purchasing_Return) {
		return purchasing_ReturnDao.update(purchasing_Return);
	}

	@Override
	public int delete(Integer id) {
		return purchasing_ReturnDao.delete(id);
	}

}
