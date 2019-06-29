package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Client;
import com.iTeam.model.Goods;
import com.iTeam.model.Sales;

public interface SalesDao {
	/**
	 * 查询所有销售出货信息
	 * @param map
	 * @return
	 */
	public List<Sales> findAll(Map<String,Object> map);
	
	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 通过编号查询出一条销售出货信息
	 * @param sNo
	 * @return
	 */
	public Sales findSalesBysNo(int sNo);
	
	/**
	 * 新增一条销售出货信息
	 * @param sales
	 * @return
	 */
	public int add(Sales sales);
	
	/**
	 * 修改销售出货信息
	 * @param sales
	 * @return
	 */
	public int update(Sales sales);
	
	/**
	 * 根据编号进行删除
	 * @param sNo
	 */
	public void deleteBysNo(int sNo);
	
	/**
	 * 根据客户No删除
	 * @param clientNo
	 */
	public int deleteByClientNo(int clientNo);
	
	/**
	 * 根据编号进行批量删除
	 * @param sNos
	 * @return
	 */
	public int deleteBatch(List<Integer> sNos);
	
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
	
}
