package com.iTeam.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.iTeam.util.CodecUtil;
import com.iTeam.util.StringUtil;

@Component
public class SimpleTokenManager implements TokenManager{

	/** 将token存储到JVM内存(ConcurrentHashMap)*/    
	private static Map<String, String> tokenMap =new ConcurrentHashMap<String, String>();
	
	
	@Override
	public String createToken(String username) {
		 String token = CodecUtil.createUUID();
	       tokenMap.put(token, username);
	       return token;
	}
	
	/**
	 * Token验证(用户登录验证)
	 */
	@Override
	public boolean checkToken(String token) {
		return !StringUtil.isEmpty(token) && tokenMap.containsKey(token);
	}

	/**
	 * Token删除(用户登出时，删除Token)
	 */
	@Override
	public void deleteToken(String token) {
		tokenMap.remove(token);
	}

}
