package com.iTeam.token;

/**
 * 登录用户的身份鉴权
 * 
 * @author guyue_NO1
 *
 */
public interface TokenManager {

	/**
	 * 创建Token
	 * @param username
	 * @return
	 */
	String createToken(String username);

	boolean checkToken(String token);

	void deleteToken(String token);

}
