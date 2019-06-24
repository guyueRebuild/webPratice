package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Provider;

/**
 * Service服务层接口
 * @author LMH
 *
 */

public interface ProviderService {

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
	
}
