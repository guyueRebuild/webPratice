package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Client;
import com.iTeam.model.Goods;
import com.iTeam.model.Sales;

public interface SalesDao {
	//查询所有销售出货信息
	public List<Sales> findAll(Map<String,Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//通过编号查询出一条销售出货信息
	public Sales findSalesBysNo(int sNo);
	
	//新增一条销售出货信息
	public int add(Sales sales);
	
	//修改销售出货信息
	public int update(Sales sales);
	
	//根据编号进行删除
	public void deleteBysNo(int sNo);
	
	public List<Goods> getGoodsList(Object object);
	
	public List<Client> getClientList(Object object);
}
