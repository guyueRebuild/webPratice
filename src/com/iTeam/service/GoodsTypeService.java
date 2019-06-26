package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.GoodsType;

/**
 * 商品类别信息Service层
 * @author XieZhiHao
 *
 */
public interface GoodsTypeService {

	//商品类别列表
	public List<GoodsType> list(Map<String,Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加商品信息
	public int add(GoodsType goodsType) throws Exception;
	
	//修改商品信息
	public int update(GoodsType goodsType) throws  Exception;
	
	//删除类别信息
	public void delete(int typeNo);
	
	/**
	 * 批量删除类别信息
	 * @param typeNos
	 */
	public void deleteBatch(List<Integer> typeNos);
}
