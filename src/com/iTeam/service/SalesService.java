package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Client;
import com.iTeam.model.Goods;
import com.iTeam.model.Sales;

public interface SalesService {
	public List<Sales> findAll(Map<String,Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public Sales findSalesBysNo(int sNo);
	
	public int add(Sales sales);
	
	public int update(Sales sales);
	
	public void deleteBysNo(int sNo);

	public List<Goods> getGoodsList(Object object);
	
	public List<Client> getClientList(Object object);
}
