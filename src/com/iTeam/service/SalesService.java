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
	
	public int add(Sales sales) throws Exception;
	
	public int update(Sales sales) throws Exception;
	
	public void deleteBysNo(int sNo);

	/**
	 * 根据客户No删除
	 * @param clientNo
	 * @return 
	 */
	public int deleteByClientNo(int clientNo);
	/**
	 * 根据客户编号进行批量删除
	 * @param clientNos
	 * @return
	 */
	public int deleteByClientNos(List<Integer> clientNos);
	
public int deleteByGoodsNo(Integer goodsNo);
	
	public int deleteByGoodsNos(List<Integer> goodsNos);
	
	public int deleteByStorageNo(Integer storageNo);
	
	public int deleteByStorageNos(List<Integer> storageNos);
	
	public List<Goods> getGoodsList(Object object);
	
	public List<Client> getClientList(Object object);

	public int deleteBatchBysNos(List<Integer> sNos);
}
