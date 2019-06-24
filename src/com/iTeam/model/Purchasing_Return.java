package com.iTeam.model;

import java.util.Date;

/**
 * 数据库封装类 
 * @author LMH
 *
 */

public class Purchasing_Return {

	private int prNo;			//采购退货单号
	private int goodsNo;		//商品编号
	private String goodsName;	//商品名称
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	private int  prQuantity;	//退货数量
	private float prPrice;		//退货单价
	private Date prDate;		//退货日期
	private int storageNo;		//退回的仓库
	private String  storageName;
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
	private String handler;		//经手人
	private String prRemark;	//采购退货备注
	
	//构造函数
	public Purchasing_Return() {
		super();
	}
	public Purchasing_Return(int prNo, int goodsNo, int prQuantity, float prPrice, Date prDate, String handler,
			String prRemark) {
		super();
		this.prNo = prNo;
		this.goodsNo = goodsNo;
		this.prQuantity = prQuantity;
		this.prPrice = prPrice;
		this.prDate = prDate;
		this.handler = handler;
		this.prRemark = prRemark;
	}
	
	//getter和setter方法
	public int getPrNo() {
		return prNo;
	}
	public void setPrNo(int prNo) {
		this.prNo = prNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getPrQuantity() {
		return prQuantity;
	}
	public void setPrQuantity(int prQuantity) {
		this.prQuantity = prQuantity;
	}
	public float getPrPrice() {
		return prPrice;
	}
	public void setPrPrice(float prPrice) {
		this.prPrice = prPrice;
	}
	public Date getPrDate() {
		return prDate;
	}
	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getPrRemark() {
		return prRemark;
	}
	public void setPrRemark(String prRemark) {
		this.prRemark = prRemark;
	}
	
	
	
}
