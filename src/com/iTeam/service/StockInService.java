package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.StockIn;

/**
 * 入库业务逻辑层
 * 
 * @author 谭昌敏
 *
 */

public interface StockInService {
	// 查询所有入库信息
	public List<StockIn> findAll(Map<String,Object> map);

	// 获取记录总数
	public int getTotal(Map<String,Object> map);

	// 添加入库信息
	public int addStockIn(StockIn stockIn);

	// 修改入库信息
	public int updateStockIn(StockIn stockIn);

	// 删除入库信息
	public int deleteByStockInNo(int stockInNo);
	
	// 根据入库单号查询入库仓库
	public int getStorageByStockInNo(int stockInNo);

	/**
	 * 批量删除入库信息
	 * @param stockInNos
	 * @return
	 */
	public int deleteByStockInNos(List<Integer> stockInNos);
	
	public int deleteByGoodsNo(Integer goodsNo);
	
	public int deleteByGoodsNos(List<Integer> goodsNos);
	
	public int deleteByStorageNo(Integer storageNo);
	
	public int deleteByStorageNos(List<Integer> storageNos);
}
