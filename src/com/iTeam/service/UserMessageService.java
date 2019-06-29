package com.iTeam.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.iTeam.model.UserMessage;
import com.iTeam.token.TokenManager;

/**
 * 用户信息Service层
 * @author XieZhiHao
 *
 */
public interface UserMessageService {
	
	/**
	 * 登陆验证
	 * @return
	 */
	public UserMessage login(UserMessage userMessage);
	/**
	 * 获取所有用户信息
	 * @param map
	 * @return
	 */
	public List<UserMessage> getUserList(Map<String, Object> map);
	/**
	 * 获取用户总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	/**
	 * 添加用户
	 * @param userMessage
	 * @return
	 */
	public int add(UserMessage userMessage) throws Exception;
	/**
	 * 修改用户
	 * @param userMessage
	 * @return
	 * @throws Exception 
	 */
	public int update(UserMessage userMessage) throws Exception;
	/**
	 * 删除用户
	 * @param userMessage
	 * @return
	 */
	public int delete(Integer userNo);
	/**
	 * 删除用户
	 * @param userNos
	 * @return
	 */
	public int deleteBatch(List<Integer> userNos);
	
	/**
	 * 退出登录
	 * @param request
	 */
	public boolean logout(HttpServletRequest request,TokenManager tokenManager);
}
