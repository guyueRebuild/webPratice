package com.iTeam.model;

import java.util.Date;

/**
 * 出库信息
 * 
 * @author 谭昌敏
 *
 */
public class StockOut {

	private int stockOutNo;// 出库单号
	private int storageNo;// 仓库编号
	private int goodsNo;// 商品编号
	private int soQuantity;// 出库数量
	private Date soDate;// 出库日期
	private String handler;// 经手人
	private String soRemark;// 出库备注

	public StockOut() {
		super();
	}

	public StockOut(int stockOutNo, int storageNo, int goodsNo, int soQuantity, Date soDate, String handler,
			String soRemark) {
		super();
		this.stockOutNo = stockOutNo;
		this.storageNo = storageNo;
		this.goodsNo = goodsNo;
		this.soQuantity = soQuantity;
		this.soDate = soDate;
		this.handler = handler;
		this.soRemark = soRemark;
	}

	public int getStockOutNo() {
		return stockOutNo;
	}

	public void setStockOutNo(int stockOutNo) {
		this.stockOutNo = stockOutNo;
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

	public int getSoQuantity() {
		return soQuantity;
	}

	public void setSoQuantity(int soQuantity) {
		this.soQuantity = soQuantity;
	}

	public Date getSoDate() {
		return soDate;
	}

	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getSoRemark() {
		return soRemark;
	}

	public void setSoRemark(String soRemark) {
		this.soRemark = soRemark;
	}

	@Override
	public String toString() {
		return "StockOut [stockOutNo=" + stockOutNo + ", storageNo=" + storageNo + ", goodsNo=" + goodsNo
				+ ", soQuantity=" + soQuantity + ", soDate=" + soDate + ", handler=" + handler + ", soRemark="
				+ soRemark + "]";
	}

}
