package com.iTeam.model;

/**
 * 数据库封装类 
 * @author LMH
 *
 */

public class Storage {

	 private int storageNo;			//仓库编号
	 private String storageName;	//仓库名称
	 private String storageAddress;	//仓库地址
	 private int capacity;			//仓库容量
	 private int cordon;			//容量警戒线
	 private int currentStorage;	//当前容量
	 
	 //构造函数
	public Storage() {
		super();
	}
	public Storage(int storageNo, String storageName, String storageAddress, int capacity, int cordon,
			int currentStorage) {
		super();
		this.storageNo = storageNo;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.capacity = capacity;
		this.cordon = cordon;
		this.currentStorage = currentStorage;
	}
	
	//getter和setter方法
	public int getStorageNo() {
		return storageNo;
	}
	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}
	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public String getStorageAddress() {
		return storageAddress;
	}
	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCordon() {
		return cordon;
	}
	public void setCordon(int cordon) {
		this.cordon = cordon;
	}
	public int getCurrentStorage() {
		return currentStorage;
	}
	public void setCurrentStorage(int currentStorage) {
		this.currentStorage = currentStorage;
	}
	 
	 
}
