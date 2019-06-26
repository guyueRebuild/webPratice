package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Client;

/**
* 供应商信息DAO层 
 * @author LMH
 *
 */

public interface ClientDao {
	
	//获取客户信息列表
	public List<Client> getClientList(Map<String, Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//添加客户信息
	public int add (Client client)throws Exception;
	
	//更新客户信息
	public int update(Client client)throws Exception;
	
	//根据客户编号删除客户信息
	public int delete(Integer clientNo);
	
	/**
	 * 批量删除客户
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
	
		
}
