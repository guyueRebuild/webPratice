package com.iTeam.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class SalesReturn {
	private int sNo;
	private int goodsNo;
	private String goodsName;
	private int srQuantity;
	private float srPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date srDate;
	private String handler;
	private int clientNo;
	private String client;
	private String srRemark;
	private int storageNo;
	private String storageName;
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
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public SalesReturn() {
		super();
	}
	public SalesReturn(int sNo, int goodsNo, String goodsName, int srQuantity, float srPrice, Date srDate,
			String handler, int clientNo, String srRemark) {
		super();
		this.sNo = sNo;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.srQuantity = srQuantity;
		this.srPrice = srPrice;
		this.srDate = srDate;
		this.handler = handler;
		this.clientNo = clientNo;
		this.srRemark = srRemark;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getSrQuantity() {
		return srQuantity;
	}
	public void setSrQuantity(int srQuantity) {
		this.srQuantity = srQuantity;
	}
	public float getSrPrice() {
		return srPrice;
	}
	public void setSrPrice(float srPrice) {
		this.srPrice = srPrice;
	}
	public Date getSrDate() {
		return srDate;
	}
	public void setSrDate(Date srDate) {
		this.srDate = srDate;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getSrRemark() {
		return srRemark;
	}
	public void setSrRemark(String srRemark) {
		this.srRemark = srRemark;
	}
	
}