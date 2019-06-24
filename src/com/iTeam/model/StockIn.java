package com.iTeam.model;

import java.util.Date;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;

/**
 * 入库信息
 * 
 * @author 谭昌敏
 *
 */

public class StockIn {

	private int stockInNo; // 入库单号
	private int storageNo; // 仓库编号
	private int goodsNo; // 商品编号
	private int siQuantity; // 入库数量
	private Date siDate; // 入库日期
	private String handler; // 经手人
	private String siRemark;// 入库备注

	public StockIn() {
		super();
	}

	public StockIn(int stockInNo, int storageNo, int goodsNo, int siQuantity, Date siDate, String handler,
			String siRemark) {
		super();
		this.stockInNo = stockInNo;
		this.storageNo = storageNo;
		this.goodsNo = goodsNo;
		this.siQuantity = siQuantity;
		this.siDate = siDate;
		this.handler = handler;
		this.siRemark = siRemark;
	}

	public int getStockInNo() {
		return stockInNo;
	}

	public void setStockInNo(int stockInNo) {
		this.stockInNo = stockInNo;
	}

	public int getStorageNo() {
		return storageNo;
	}

	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}

	public int getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}

	public int getsiQuantity() {
		return siQuantity;
	}

	public void setsiQuantity(int siQuantity) {
		this.siQuantity = siQuantity;
	}

	public Date getsiDate() {
		return siDate;
	}

	public void setsiDate(Date siDate) {
		this.siDate = siDate;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getsiRemark() {
		return siRemark;
	}

	public void setsiRemark(String siRemark) {
		this.siRemark = siRemark;
	}

	@Override
	public String toString() {
		return "StockIn [stockInNo=" + stockInNo + ", storageNo=" + storageNo + ", goodsNo=" + goodsNo + ", siQuantity="
				+ siQuantity + ", siDate=" + siDate + ", handler=" + handler + ", siRemark=" + siRemark + "]";
	}

}
