package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Storage;

/**
 * Service服务层接口
 * @author LMH
 *
 */

public interface StorageService {
	
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
	
	//根据仓库编号更新仓库容量
	public int updateCurrentStorage(Storage storage);
}
