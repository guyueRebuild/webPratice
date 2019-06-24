package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Purchasing;

/**
* 供应商信息DAO层 
 * @author LMH
 *
 */

public interface PurchasingDao {
	
	//获取采购信息列表
	public List<Purchasing> getPurchasingList(Map<String, Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加采购信息
	public int add(Purchasing purchasing);
	
	//更新采购信息
	public int update(Purchasing purchasing);
	
	//根据采购编号删除采购信息
	public int delete(Integer id);
	
}
