package com.iTeam.dao;

import java.util.List;

import com.iTeam.model.Inventory;

/**
 * 仓库信息查询
 * @author guyue_NO1
 *
 */
public interface InventoryDao {
	
	/**
	 * 根据仓库编号获取仓库存货列表
	 * @param storageNo
	 * @return
	 */
	public List<Inventory> getStockInInventory(int storageNo);
	
	/**
	 * 根据仓库编号获取仓库出货列表
	 * @param storageNo
	 * @return
	 */
	public List<Inventory> getStockOutInventory(int storageNo);
	
	/**
	 * 根据仓库编号或取该仓库进货数量
	 * @param storageNo
	 * @return
	 */
	public int getStorageInInventory(int storageNo);
	
	/**
	 * 根据仓库编号或取该仓库出货数量
	 * @param storageNo
	 * @return
	 */
	public int getStorageOutInventory(int storageNo);
}
