package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.Purchasing_ReturnDao;
import com.iTeam.model.Purchasing_Return;
import com.iTeam.service.Purchasing_ReturnService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	public int add(Purchasing_Return purchasing_Return) throws Exception {
		try {
			return purchasing_ReturnDao.add(purchasing_Return);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int update(Purchasing_Return purchasing_Return) throws Exception {
		try {
			return purchasing_ReturnDao.update(purchasing_Return);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public int delete(Integer id) {
		return purchasing_ReturnDao.delete(id);
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		return purchasing_ReturnDao.deleteBatch(ids);
	}

	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return purchasing_ReturnDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return purchasing_ReturnDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return purchasing_ReturnDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return purchasing_ReturnDao.deleteByStorageNos(storageNos);
	}
}
