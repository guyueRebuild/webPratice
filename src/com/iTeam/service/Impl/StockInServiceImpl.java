package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.StockInDao;
import com.iTeam.model.StockIn;
import com.iTeam.service.StockInService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * 实现入库业务逻辑层和调用mapper层进行数据操作
 * 
 * @author 谭昌敏
 *
 */

@Service
@Transactional
public class StockInServiceImpl implements StockInService {

	@Autowired
	private StockInDao stockInDao;

	@Override
	public List<StockIn> findAll(Map<String, Object> map) {	
		return stockInDao.findAll(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return stockInDao.getTotal(map);
	}

	@Override
	public int addStockIn(StockIn stockIn) throws Exception {
		try {
			return stockInDao.addStockIn(stockIn);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int updateStockIn(StockIn stockIn) throws Exception {
		try {
			return stockInDao.updateStockIn(stockIn);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int deleteByStockInNo(int stockInNo) {
		return stockInDao.deleteByStockInNo(stockInNo);
	}

	@Override
	public int getStorageByStockInNo(int stockInNo) {
		return stockInDao.getStorageByStockInNo(stockInNo);
	}

	@Override
	public int deleteByStockInNos(List<Integer> stockInNos) {
		return stockInDao.deleteByStockInNos(stockInNos);
	}
	
	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return stockInDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return stockInDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return stockInDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return stockInDao.deleteByStorageNos(storageNos);
	}


}
