package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Storage;

/**
 * 供应商信息DAO层
 * @author LMH
 *
 */

public interface StorageDao {
	
	//获取仓库信息列表
	public List<Storage> getStorageList(Map<String, Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String, Object> map);
	
	//添加仓库信息
	public int add(Storage storage);
	
	//更新仓库信息
	public int update(Storage storage);
	
	//根据仓库编号删除仓库信息
	public int delete(Integer id);
	
	/**
	 * 根据仓库编号批量删除仓库信息
	 * @param ids
	 * @return 
	 */
	public int deleteBatch(List<Integer> ids);
	
	public int updateCurrentStorage(Storage storage);
}
