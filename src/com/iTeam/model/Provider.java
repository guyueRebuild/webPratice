package com.iTeam.model;

/**
 * 数据库封装类 
 * @author LMH
 *
 */

public class Provider {

	private int providerNo;		//供应商编号
	private String provider;	//供应商名称
	private String pAddress;	//供应商地址
	private String pLinkman;	//联系人
	private String pEmail;		//联系邮件
	private String pPhone;		//联系电话
	
	//构造函数
	public Provider() {
		super();
	}
	public Provider(int providerNo, String provider, String pAddress, String pLinkman, String pEmail, String pPhone) {
		super();
		this.providerNo = providerNo;
		this.provider = provider;
		this.pAddress = pAddress;
		this.pLinkman = pLinkman;
		this.pEmail = pEmail;
		this.pPhone = pPhone;
	}
	
	//getter和setter方法
	public int getProviderNo() {
		return providerNo;
	}
	public void setProviderNo(int providerNo) {
		this.providerNo = providerNo;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getpAddress() {
		return pAddress;
	}
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}
	public String getpLinkman() {
		return pLinkman;
	}
	public void setpLinkman(String pLinkman) {
		this.pLinkman = pLinkman;
	}
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	public String getpPhone() {
		return pPhone;
	}
	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}
	
}
