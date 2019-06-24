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
	public int add(Sales sales) {
		return salesDao.add(sales);
		
	}


	@Override
	public int update(Sales sales) {
		return salesDao.update(sales);
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

}
