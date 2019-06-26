package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Goods;

/**
 * 商品信息DAO层
 * @author XieZhiHao
 *
 */
public interface GoodsDao {

	//获取商品信息列表
	public List<Goods> getGoodsList(Map<String,Object> map);
	
	public List<Integer> getGoodsNoListByTypeNo(Integer typeNo);
	
	public List<Integer> getGoodNoListByTypeNos(List<Integer> typeNos);
	
	public List<Integer> getGoodsNoListByProviderNo(Integer providerNo);
	
	public List<Integer> getGoodNoListByProviderNos(List<Integer> typeNos);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加商品信息
	public int add(Goods goods);
	
	//修改商品信息
	public int update(Goods goods);
	
	//删除商品信息
	public void delete(int goodsNo);
	
	/**
	 * 批量删除商品
	 * @param goodsNos
	 * @return 
	 */
	public int deleteBatch(List<Integer> goodsNos);
	
	
}
