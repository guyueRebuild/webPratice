package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Provider;

/**
 * 供应商信息DAO层
 *
 */

public interface ProviderDao {

	/**
	 * 获取供应商信息列表
	 * @param map
	 * @return
	 */
	public List<Provider> getProviderList(Map<String, Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	
	/**
	 * 新增供应商信息
	 * @param provider
	 * @return
	 */
	public int add(Provider provider);
	
	/**
	 * 更新供应商信息
	 * @param provider
	 * @return
	 */
	public int update(Provider provider);
	
	/**
	 * 根据供应商编号删除商品信息
	 * @param id
	 * @return
	 */
	public int delete(Integer id);	
	
	/**
	 * 批量删除供应商信息
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
}
