package com.iTeam.model;

/**
 * 商品信息实体类
 * @author XieZhiHao
 *
 */
public class Goods {

	private int goodsNo;					//商品编号
	private String goodsName;				//商品名称
	private int typeNo;						//类型编号
	private String type;					//类型名称
	private int providerNo;					//供应商编号
	private String provider;				//供应商名称
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	private String specification;			//商品规格
	private String gRemark;					//备注
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	public int getProviderNo() {
		return providerNo;
	}
	public void setProviderNo(int providerNo) {
		this.providerNo = providerNo;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getgRemark() {
		return gRemark;
	}
	public void setgRemark(String gRrmark) {
		this.gRemark = gRrmark;
	}
	
}
