package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.StockOut;

/**
 * 出库数据访问接口
 * 
 * @author 谭昌敏
 *
 */
public interface StockOutDao {
	// 查询所有出库信息
	public List<StockOut> findAll(Map<String,Object> map);
	
	// 获取记录总数
	public int getTotal(Map<String,Object> map);

	// 添加出库信息
	public int addStockOut(StockOut stockOut);

	// 修改出库信息
	public int updateStockOut(StockOut stockOut);

	// 删除出库信息
	public int deleteByStockOutNo(int stockOutNo);
	
	// 根据出库单号查询出库仓库
	public int getStorageByStockOutNo(int stockOutNo);
	
	/**
	 * 
	 * @param typeNos
	 * @return 
	 */
	public int deleteBatch(List<Integer> stockOutNos);
	
	public int deleteByGoodsNo(Integer goodsNo);
	
	public int deleteByGoodsNos(List<Integer> goodsNos);
	
	public int deleteByStorageNo(Integer storageNo);
	
	public int deleteByStorageNos(List<Integer> storageNos);
}
