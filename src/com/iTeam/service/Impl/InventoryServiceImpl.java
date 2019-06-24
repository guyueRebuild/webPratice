package com.iTeam.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iTeam.dao.InventoryDao;
import com.iTeam.model.Inventory;
import com.iTeam.service.InventoryService;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDao mapper;
	
	@Override
	public List<Inventory> getStockInInventory(int storageNo) {
		return mapper.getStockInInventory(storageNo);
	}

	@Override
	public List<Inventory> getStockOutInventory(int storageNo) {
		return mapper.getStockOutInventory(storageNo);
	}

	@Override
	public int getStorageInInventory(int storageNo) {
		return mapper.getStorageInInventory(storageNo);
	}

	@Override
	public int getStorageOutInventory(int storageNo) {
		return mapper.getStorageOutInventory(storageNo);
	}

}
