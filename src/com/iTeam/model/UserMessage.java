package com.iTeam.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

/**
 * 用户信息类
 * @author XieZhiHao
 *
 */
public class UserMessage {

	private int userNo;						//用户编号
	@NotEmpty
	private String userName;				//用户名
	private String nickName;				//昵称
	@NotEmpty
	private String password;				//密码
	private String userPhone;				//联系方式
	private String userEmail;				//邮箱
	private Date userDate;					//创建时间
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	
}
