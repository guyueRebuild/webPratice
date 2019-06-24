package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.GoodsType;

/**
 * 商品信息DAO层
 * @author XieZhiHao
 *
 */
public interface GoodsTypeDao {

	//获取商品类别信息列表
	public List<GoodsType> list(Map<String,Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加类别信息
	public int add(GoodsType goodsType);
	
	//修改类别信息
	public int update(GoodsType goodsType);
	
	//删除类别信息
	public void delete(int typeNo);
}
