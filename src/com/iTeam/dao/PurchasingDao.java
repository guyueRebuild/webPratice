package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Purchasing;

/**
* 供应商信息DAO层 
 *
 */

public interface PurchasingDao {
	
	/**
	 * 获取采购信息列表
	 * @param map
	 * @return
	 */
	public List<Purchasing> getPurchasingList(Map<String, Object> map);
	
	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加采购信息
	 * @param purchasing
	 * @return
	 */
	public int add(Purchasing purchasing);
	
	/**
	 * 更新采购信息
	 * @param purchasing
	 * @return
	 */
	public int update(Purchasing purchasing);
	
	/**
	 * 根据采购编号删除采购信息
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 根据供应商编号列表删除采购信息
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
	
	/**
	 * 根据商品编号删除采购信息
	 * @param goodsNo
	 * @return
	 */
	public int deleteByGoodsNo(Integer goodsNo);
	
	/**
	 * 根据商品编号列表删除采购信息
	 * @param goodsNo
	 * @return
	 */
	public int deleteByGoodsNos(List<Integer> goodsNos);
	
	/**
	 * 根据仓库编号删除采购信息
	 * @param goodsNo
	 * @return
	 */
	public int deleteByStorageNo(Integer storageNo);
	
	/**
	 * 根据仓库编号列表删除采购信息
	 * @param goodsNo
	 * @return
	 */
	public int deleteByStorageNos(List<Integer> storageNos);
	
}
