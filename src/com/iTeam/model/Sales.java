package com.iTeam.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class Sales {
	private int sNo;
	private int goodsNo;
	private String goodsName;
	private int quantitySale;
	private float priceSale;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date dateSale;
	private String handler;
	private int clientNo;
	private String client;
	private String sRemark;
	private int storageNo;
	private String storageName;
	
	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public int getStorageNo() {
		return storageNo;
	}
	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}
	public String  getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Sales() {
		super();
	}
	public Sales(int sNo, int goodsNo, String goodsName, int quantitySale, float priceSale, Date dateSale,
			String handler, int clientNo, String sRemark) {
		super();
		this.sNo = sNo;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.quantitySale = quantitySale;
		this.priceSale = priceSale;
		this.dateSale = dateSale;
		this.handler = handler;
		this.clientNo = clientNo;
		this.sRemark = sRemark;
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
	public int getQuantitySale() {
		return quantitySale;
	}
	public void setQuantitySale(int quantitySale) {
		this.quantitySale = quantitySale;
	}
	public float getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(float priceSale) {
		this.priceSale = priceSale;
	}
	public Date getDateSale() {
		return dateSale;
	}
	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
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
	public String getsRemark() {
		return sRemark;
	}
	public void setsRemark(String sRemark) {
		this.sRemark = sRemark;
	}
}