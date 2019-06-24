package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.StockInDao;
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
	private StockOutDao mapper;

	@Override
	public List<StockOut> findAll(Map<String, Object> map) {
		return mapper.findAll(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return mapper.getTotal(map);
	}

	@Override
	public int addStockOut(StockOut stockOut) {
		return mapper.addStockOut(stockOut);
	}

	@Override
	public int updateStockOut(StockOut StockOut) {
		return mapper.updateStockOut(StockOut);
	}

	@Override
	public int deleteByStockOutNo(int StockOutNo) {
		return mapper.deleteByStockOutNo(StockOutNo);
	}

	@Override
	public int getStorageByStockInNo(int stockInNo) {
		return mapper.getStorageByStockInNo(stockInNo);
	}

	

}
