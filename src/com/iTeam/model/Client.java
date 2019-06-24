package com.iTeam.model;

/**
 * 数据库封装类 
 * @author LMH
 *
 */

public class Client {

	private int clientNo;		//客户编号
	private String client;		//客户名称	
	private String cAddress;	//客户地址
	private String cEmail;		//客户邮箱
	private String cPhone;		//客户电话
	
	//构造函数
	public Client() {
		super();
	}
	public Client(int clientNo, String client, String cAddress, String cEmail, String cPhone) {
		super();
		this.clientNo = clientNo;
		this.client = client;
		this.cAddress = cAddress;
		this.cEmail = cEmail;
		this.cPhone = cPhone;
	}
	
	//getter和setter方法
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	
}
