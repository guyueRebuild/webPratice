package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.SalesDao;
import com.iTeam.model.Client;
import com.iTeam.model.Goods;
import com.iTeam.model.Sales;
import com.iTeam.service.SalesService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * 
 * 业务层实现事务控制和调用mapper层进行数据操作
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesDao salesDao;

	@Override
	public List<Sales> findAll(Map<String,Object> map) {
		return salesDao.findAll(map);
	}

	@Override
	public int add(Sales sales) throws Exception {
		try {
			return salesDao.add(sales);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
		
	}

	@Override
	public int update(Sales sales) throws Exception {
		try {
			return salesDao.update(sales);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public void deleteBysNo(int sNo) {
		salesDao.deleteBysNo(sNo);
		
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return salesDao.getTotal(map);
	}

	@Override
	public Sales findSalesBysNo(int sNo) {
		return salesDao.findSalesBysNo(sNo);
	}

	@Override
	public List<Goods> getGoodsList(Object object) {
		return salesDao.getGoodsList(null);
	}

	@Override
	public List<Client> getClientList(Object object) {

		return salesDao.getClientList(null);
	}

	@Override
	public int deleteBatchBysNos(List<Integer> sNos) {
		return salesDao.deleteBatch(sNos);
	}

	@Override
	public int deleteByClientNo(int clientNo) {
		return salesDao.deleteByClientNo(clientNo);
	}

	@Override
	public int deleteByClientNos(List<Integer> clientNos) {
		return salesDao.deleteByClientNos(clientNos);
	}

	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return salesDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return salesDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return salesDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return salesDao.deleteByStorageNos(storageNos);
	}

	

}
