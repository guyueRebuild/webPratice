package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.ClientDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Client;
import com.iTeam.service.ClientService;
import com.iTeam.service.SalesReturnService;
import com.iTeam.service.SalesService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Service服务层实现类
 * 
 * @author LMH
 *
 */

@Service("ClientService")
public class ClientServiceImpl implements ClientService {

	// 使用spring核心技术的IOC/DI功能进行依赖注入--实例化DAO接口
	@Resource
	private ClientDao clientDao;

	@Resource
	private SalesService salesService;

	@Resource
	private SalesReturnService salesReturnService;

	@Override
	public List<Client> getClientList(Map<String, Object> map) {
		return clientDao.getClientList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return clientDao.getTotal(map);
	}

	@Override
	public int add(Client client) throws Exception {
		try {
			return clientDao.add(client);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}

	}

	@Override
	public int update(Client client) throws Exception {
		try {
			return clientDao.update(client);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public int delete(Integer id) {
		int result = 0;
		try {
			// 先删除销售表和销售退货表
			// 根据客户id删除销售订单
			salesService.deleteByClientNo(id);
			// 根据客户id删除销售退货订单
			salesReturnService.deleteByClientNo(id);
			result = clientDao.delete(id);
		} catch (Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
		return result;
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		int result = 0;
		try {
			salesService.deleteByClientNos(ids);
			salesReturnService.deleteByClientNos(ids);
			result = clientDao.deleteBatch(ids);
		} catch (Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
		return result;
	}

}
