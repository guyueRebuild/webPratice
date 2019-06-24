package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Purchasing_Return;

public interface Purchasing_ReturnService {
	
	//获取采购退货信息列表
	public List<Purchasing_Return> getPurchasing_ReturnList(Map<String, Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加采购退货信息
	public int add(Purchasing_Return purchasing_Return);
	
	//更新采购退货信息
	public int update(Purchasing_Return purchasing_Return);
	
	//根据采购编号删除采购退货信息
	public int delete(Integer id);
	
}
