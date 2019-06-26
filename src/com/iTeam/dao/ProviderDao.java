package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Provider;

/**
 * 供应商信息DAO层
 * @author LMH
 *
 */

public interface ProviderDao {

	//获取供应商信息列表
	public List<Provider> getProviderList(Map<String, Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String, Object> map);
	
	//添加商品信息
	public int add(Provider provider);
	
	//修改商品信息
	public int update(Provider provider);
	
	//根据商品编号删除商品信息
	public int delete(Integer id);	
	
	/**
	 * 批量删除供应商信息
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
}
