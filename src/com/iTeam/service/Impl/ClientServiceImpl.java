package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.ClientDao;
import com.iTeam.model.Client;
import com.iTeam.service.ClientService;

/**
 * Service服务层实现类
 * @author LMH
 *
 */

@Service("ClientService")
public class ClientServiceImpl implements ClientService {

	// 使用spring核心技术的IOC/DI功能进行依赖注入--实例化DAO接口
	@Resource
	private ClientDao clientDao;

	@Override
	public List<Client> getClientList(Map<String, Object> map) {
		return clientDao.getClientList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return clientDao.getTotal(map);
	}

	@Override
	public int add(Client client) {
		return clientDao.add(client);
	}

	@Override
	public int update(Client client) {
		return clientDao.update(client);
	}

	@Override
	public int delete(Integer id) {
		return clientDao.delete(id);
	}
	
}

