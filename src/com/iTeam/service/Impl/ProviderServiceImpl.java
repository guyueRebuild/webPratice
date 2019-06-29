package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.ProviderDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Provider;
import com.iTeam.service.GoodsService;
import com.iTeam.service.ProviderService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


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
	
	@Resource
	private GoodsService goodsService;
	
	@Override
	public List<Provider> getProviderList(Map<String, Object> map) {
		return providerDao.getProviderList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return providerDao.getTotal(map);
	}

	@Override
	public int add(Provider provider) throws Exception {
		try {
			return providerDao.add(provider);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int update(Provider provider) throws Exception {
		try {
			return providerDao.update(provider);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		} 
	}

	@Override
	public int delete(Integer id) {
		try {
			//对供应商类型有约束的表：商品，
			//根据供应商编号删除对应商品
			List<Integer> goodsNos = goodsService.getGoodsNoListByProviderNo(id);
			goodsService.deleteBatch(goodsNos);
			return providerDao.delete(id);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
		
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		try {
			//对供应商类型有约束的表：商品，
			//根据供应商编号删除对应商品
			List<Integer> goodsNos = goodsService.getGoodNoListByProviderNos(ids);
			goodsService.deleteBatch(goodsNos);
			return providerDao.deleteBatch(ids);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}	
	}

}
