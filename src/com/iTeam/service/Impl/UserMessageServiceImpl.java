package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.UserMessageDao;
import com.iTeam.model.UserMessage;
import com.iTeam.service.UserMessageService;

@Service("UserMessageService")
public class UserMessageServiceImpl implements UserMessageService {

	@Resource
	private UserMessageDao userMessageDao;
	
	@Override
	public UserMessage login(UserMessage userMessage) {
		return userMessageDao.login(userMessage);
	}

	@Override
	public List<UserMessage> getUserList(Map<String, Object> map) {
		return userMessageDao.getUserList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return userMessageDao.getTotal(map);
	}

	@Override
	public int add(UserMessage userMessage) {
		return userMessageDao.add(userMessage);
	}

	@Override
	public int update(UserMessage userMessage) {
		return userMessageDao.update(userMessage);
	}

	@Override
	public int delete(Integer userNo) {
		return userMessageDao.delete(userNo);
	}

}
