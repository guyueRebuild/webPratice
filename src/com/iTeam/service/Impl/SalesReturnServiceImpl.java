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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	public int add(SalesReturn sr) throws Exception {
		try {
			return srDao.add(sr);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int update(SalesReturn sr) throws Exception {
		try {
			return srDao.update(sr);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public void deleteBysNo(int sNo) {
		srDao.deleteBysNo(sNo);

	}

	@Override
	public int deleteBatchBysNos(List<Integer> sNos) {
		return srDao.deleteBatch(sNos);
	}

	@Override
	public int deleteByClientNo(int clientNo) {
		return srDao.deleteByClientNo(clientNo);
		
	}

	@Override
	public int deleteByClientNos(List<Integer> clientNos) {
		return srDao.deleteByClientNos(clientNos);
	}

	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return srDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return srDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return srDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return srDao.deleteByStorageNos(storageNos);
	}

}
