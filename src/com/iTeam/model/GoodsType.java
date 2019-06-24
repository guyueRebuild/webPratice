package com.iTeam.model;

import java.util.Date;

/**
 * 商品类型实体类
 * @author XieZhiHao
 *
 */
public class GoodsType {

	private int typeNo;
	private String type;
	private Date createTime;
	
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
