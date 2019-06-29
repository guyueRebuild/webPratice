package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Goods;

/**
 * 商品信息DAO层
 *
 */
public interface GoodsDao {

	/**
	 * 获取商品信息列表
	 * @param map
	 * @return
	 */
	public List<Goods> getGoodsList(Map<String,Object> map);
	
	/**
	 * 根据商品类型Id获取商品信息列表
	 * @param typeNo
	 * @return
	 */
	public List<Integer> getGoodsNoListByTypeNo(Integer typeNo);
	
	/**
	 * 根据商品类型Id列表获取商品信息列表
	 * @param typeNos
	 * @return
	 */
	public List<Integer> getGoodNoListByTypeNos(List<Integer> typeNos);
	
	/**
	 * 根据供应商Id获取商品信息列表
	 * @param providerNo
	 * @return
	 */
	public List<Integer> getGoodsNoListByProviderNo(Integer providerNo);
	
	/**
	 * 根据供应商Id列表获取商品信息列表
	 * @param typeNos
	 * @return
	 */
	public List<Integer> getGoodNoListByProviderNos(List<Integer> typeNos);
	
	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加商品信息
	 * @param goods
	 * @return
	 */
	public int add(Goods goods);
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	public int update(Goods goods);
	
	/**
	 * 删除商品信息
	 * @param goodsNo
	 */
	public void delete(int goodsNo);
	
	/**
	 * 批量删除商品
	 * @param goodsNos
	 * @return 
	 */
	public int deleteBatch(List<Integer> goodsNos);
	
	
}
