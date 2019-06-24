package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.StockIn;
import com.iTeam.model.StockOut;

/**
 * 出库业务逻辑层
 * 
 * @author 谭昌敏
 *
 */

public interface StockOutService {

	// 查询所有出库信息
	public List<StockOut> findAll(Map<String, Object> map);

	// 获取记录总数
	public int getTotal(Map<String, Object> map);

	// 添加出库信息
	public int addStockOut(StockOut StockOut);

	// 修改出库信息
	public int updateStockOut(StockOut StockOut);

	// 删除出库信息
	public int deleteByStockOutNo(int StockOutNo);
	
	// 根据出库单号查询出库仓库
	public int getStorageByStockInNo(int stockInNo);
}
