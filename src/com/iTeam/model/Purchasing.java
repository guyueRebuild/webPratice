package com.iTeam.model;

import java.util.Date;

/**
 * 数据库封装类 
 * @author LMH
 *
 */

public class Purchasing {
	
	private int pNo;			//采购进货单号
	private int goodsNo;		//商品编号
	private String goodsName;	//商品名称
	private int storageNo;		//仓库编号
	private String storageName;	//仓库名称
	private int quantityIn;		//进货数量
	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	private float priceIn;		//进货单价
	private Date dateIn;		//进货日期
	public String getGoodsName() {
		return goodsName;
	}
	public int getStorageNo() {
		return storageNo;
	}
	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	private String handler;		//经手人
	private String pRemark;		//采购进货备注
	
	//构造函数
	public Purchasing() {
		super();
	}
	public Purchasing(int pNo, int goodsNo, int quantityIn, float priceIn, Date dateIn, String handler,
			String pRemark) {
		super();
		this.pNo = pNo;
		this.goodsNo = goodsNo;
		this.quantityIn = quantityIn;
		this.priceIn = priceIn;
		this.dateIn = dateIn;
		this.handler = handler;
		this.pRemark = pRemark;
	}
	
	//getter和setter方法
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getQuantityIn() {
		return quantityIn;
	}
	public void setQuantityIn(int quantityIn) {
		this.quantityIn = quantityIn;
	}
	public float getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(float priceIn) {
		this.priceIn = priceIn;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getpRemark() {
		return pRemark;
	}
	public void setpRemark(String pRemark) {
		this.pRemark = pRemark;
	}
	
	
}
