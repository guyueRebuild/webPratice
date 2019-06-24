package com.iTeam.service;

import java.util.List;

import com.iTeam.model.Inventory;

public interface InventoryService {

	public List<Inventory> getStockInInventory(int storageNo);

	public List<Inventory> getStockOutInventory(int storageNo);
	
	public int getStorageInInventory(int storageNo);
	
	public int getStorageOutInventory(int storageNo);
}
