package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.UserMessage;

/**
 * 用户信息DAO层
 * @author XieZhiHao
 *
 */
public interface UserMessageDao {
	
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
	public int add(UserMessage userMessage);
	/**
	 * 修改用户
	 * @param userMessage
	 * @return
	 */
	public int update(UserMessage userMessage);
	/**
	 * 删除用户
	 * @param userMessage
	 * @return
	 */
	public int delete(Integer userNo);
	
	/**
	 * 批量删除用户
	 * @param userNo
	 * @return 
	 */
	public int deleteBatch(List<Integer> userNos);
}
