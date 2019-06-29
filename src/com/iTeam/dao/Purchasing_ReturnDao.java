package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Purchasing_Return;

/**
* 供应商信息DAO层 
 *
 */

public interface Purchasing_ReturnDao {
	
	/**
	 * 获取采购退货信息列表
	 * @param map
	 * @return
	 */
	public List<Purchasing_Return> getPurchasing_ReturnList(Map<String, Object> map);
	
	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加采购退货信息
	 * @param purchasing_Return
	 * @return
	 */
	public int add(Purchasing_Return purchasing_Return);
	
	/**
	 * 更新采购退货信息
	 * @param purchasing_Return
	 * @return
	 */
	public int update(Purchasing_Return purchasing_Return);
	
	/**
	 * 根据采购编号删除采购退货信息
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	
	/**
	 * 批量删除采购退货信息
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
	
	/**
	 * 根据商品类型删除该采购退货信息
	 * @param goodsNo
	 * @return
	 */
	public int deleteByGoodsNo(Integer goodsNo);
	
	/**
	 * 根据商品列表删除采购退货信息
	 * @param goodsNos
	 * @return
	 */
	public int deleteByGoodsNos(List<Integer> goodsNos);
	
	/**
	 * 根据仓库编号删除采购回退信息
	 * @param storageNo
	 * @return
	 */
	public int deleteByStorageNo(Integer storageNo);
	
	/**
	 * 根据仓库编号列表删除采购回退信息
	 * @param storageNos
	 * @return
	 */
	public int deleteByStorageNos(List<Integer> storageNos);
	
}
