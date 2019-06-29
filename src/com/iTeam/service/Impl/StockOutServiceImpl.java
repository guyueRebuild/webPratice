package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.StockOutDao;
import com.iTeam.model.StockOut;
import com.iTeam.service.StockOutService;

/**
 * 实现出库业务逻辑层和调用mapper层进行数据操作
 * 
 * @author 谭昌敏
 *
 */

@Service
@Transactional
public class StockOutServiceImpl implements StockOutService {

	@Autowired
	private StockOutDao stockOutDao;

	@Override
	public List<StockOut> findAll(Map<String, Object> map) {
		return stockOutDao.findAll(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return stockOutDao.getTotal(map);
	}

	@Override
	public int addStockOut(StockOut stockOut) {
		return stockOutDao.addStockOut(stockOut);
	}

	@Override
	public int updateStockOut(StockOut StockOut) {
		return stockOutDao.updateStockOut(StockOut);
	}

	@Override
	public int deleteByStockOutNo(int StockOutNo) {
		return stockOutDao.deleteByStockOutNo(StockOutNo);
	}

	@Override
	public Integer getStorageByStockOutNo(int stockInNo) {
		return stockOutDao.getStorageByStockOutNo(stockInNo);
	}

	@Override
	public int deleteByStockOutNos(List<Integer> stockOutNos) {
		return stockOutDao.deleteBatch(stockOutNos);
	}
	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return stockOutDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return stockOutDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return stockOutDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return stockOutDao.deleteByStorageNos(storageNos);
	}


}
