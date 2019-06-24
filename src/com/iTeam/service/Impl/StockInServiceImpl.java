package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.StockInDao;
import com.iTeam.model.StockIn;
import com.iTeam.service.StockInService;

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
	private StockInDao mapper;

	@Override
	public List<StockIn> findAll(Map<String, Object> map) {	
		return mapper.findAll(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return mapper.getTotal(map);
	}

	@Override
	public int addStockIn(StockIn stockIn) {
		return mapper.addStockIn(stockIn);
	}

	@Override
	public int updateStockIn(StockIn stockIn) {
		return mapper.updateStockIn(stockIn);
	}

	@Override
	public int deleteByStockInNo(int stockInNo) {
		return mapper.deleteByStockInNo(stockInNo);
	}

	@Override
	public int getStorageByStockInNo(int stockInNo) {
		return mapper.getStorageByStockInNo(stockInNo);
	}

}
