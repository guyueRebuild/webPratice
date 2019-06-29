package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.GoodsType;

/**
 * 商品信息DAO层
 *
 */
public interface GoodsTypeDao {

	/**
	 * 获取商品类别信息列表
	 * 
	 * @param map
	 * @return
	 */
	public List<GoodsType> list(Map<String, Object> map);

	/**
	 * 获取记录总数
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);

	/**
	 * 添加类别信息
	 * 
	 * @param goodsType
	 * @return
	 */
	public int add(GoodsType goodsType);

	/**
	 * 修改类别信息
	 * 
	 * @param goodsType
	 * @return
	 */
	public int update(GoodsType goodsType);

	/**
	 * 删除类别信息
	 * 
	 * @param typeNo
	 */
	public void delete(int typeNo);

	/**
	 * 批量删除类别信息
	 * 
	 * @param typeNos
	 */
	public void deleteBatch(List<Integer> typeNos);
}
