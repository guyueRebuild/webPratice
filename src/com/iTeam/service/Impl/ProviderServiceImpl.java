package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.ProviderDao;
import com.iTeam.model.Provider;
import com.iTeam.service.ProviderService;

/**
 * Service服务层实现类
 * @author LMH
 *
 */

@Service("ProviderService")
public class ProviderServiceImpl implements ProviderService {

	// 使用spring核心技术的IOC/DI功能进行依赖注入--实例化DAO接口
	@Resource
	private ProviderDao providerDao;
	
	@Override
	public List<Provider> getProviderList(Map<String, Object> map) {
		return providerDao.getProviderList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return providerDao.getTotal(map);
	}

	@Override
	public int add(Provider provider) {
		return providerDao.add(provider);
	}

	@Override
	public int update(Provider provider) {
		return providerDao.update(provider);
		
	}

	@Override
	public int delete(Integer id) {
		return providerDao.delete(id);
		
	}
}
