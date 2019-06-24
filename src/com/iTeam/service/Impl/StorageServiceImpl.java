package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.StorageDao;
import com.iTeam.model.Storage;
import com.iTeam.service.StorageService;

/**
 * Service服务层实现类
 * @author LMH
 *
 */

@Service("StorageService")
public class StorageServiceImpl implements StorageService {

	// 使用spring核心技术的IOC/DI功能进行依赖注入--实例化DAO接口
	@Resource
	private StorageDao storageDao;

	@Override
	public List<Storage> getStorageList(Map<String, Object> map) {
		return storageDao.getStorageList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return storageDao.getTotal(map);
	}

	@Override
	public int add(Storage storage) {
		return storageDao.add(storage);
	}

	@Override
	public int update(Storage storage) {
		return storageDao.update(storage);
	}

	@Override
	public int delete(Integer id) {
		return storageDao.delete(id);
	}

	@Override
	public int updateCurrentStorage(Storage storage) {
		return storageDao.updateCurrentStorage(storage);
	}
	
}


