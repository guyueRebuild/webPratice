package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Sales;
import com.iTeam.model.SalesReturn;

public interface SalesReturnService {
	
	
	//查询所有销售退货货信息
	public List<SalesReturn> findAll(Map<String,Object> map);
	
	//获取记录总数
	public Long getTotal(Map<String,Object> map);
	
	//通过编号查询出一条销售退货信息
	public Sales findSalesBysNo(int sNo);
	
	//新增一条销售退货信息
	public int add(SalesReturn sr) throws Exception;
	
	//修改销售退货信息
	public int update(SalesReturn sr) throws Exception;
	
	//根据编号进行删除
	public void deleteBysNo(int sNo);
	
	/**
	 * 根据编号进行批量删除
	 * @param ids
	 * @return
	 */
	public int deleteBatchBysNos(List<Integer> sNos);
	
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
}
