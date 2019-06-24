package com.iTeam.model;

public class Inventory {

	//仓库编号
	private int storageNo;
	
	//商品名称
	private String goodsName;
	
	//商品数量
	private int quantity;

	public Inventory() {
		super();
	}

	public Inventory(int storageNo, String goodsName, int quantity) {
		super();
		this.storageNo = storageNo;
		this.goodsName = goodsName;
		this.quantity = quantity;
	}

	public int getStorageNo() {
		return storageNo;
	}

	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [storageNo=" + storageNo + ", goodsName=" + goodsName + ", quantity=" + quantity
				+ "]";
	}
}
