package com.iTeam.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.annotion.IgnoreSecurity;
import com.iTeam.exception.UserNotFoundException;
import com.iTeam.model.PageBean;
import com.iTeam.model.UserMessage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.UserMessageService;
import com.iTeam.token.TokenManager;
import com.iTeam.util.MyConstants;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

/**
 * 用户信息控制层
 * 
 * @author XieZhiHao
 *
 */
@RestController
@RequestMapping("/")
public class UserMessageController {

	@Resource
	private UserMessageService userMessageService;
	
	@Resource
	private TokenManager tokenManager;
	
	private static final Logger log = Logger.getLogger(UserMessageController.class);
	
	private final String PRODUCES = "application/json";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	/**
	 * 登陆验证
	 * 
	 * @param userMessage
	 * @param request
	 * @return 用户名 类型：String
	 */
	@IgnoreSecurity
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse login(@RequestBody UserMessage userMessage,HttpServletResponse response) throws Exception {
		UserMessage result = userMessageService.login(userMessage);
		if (result == null) {
			throw new UserNotFoundException(userMessage.getUserName());
		} else {
			String token = tokenManager.createToken(result.getUserName());
			log.debug("**** Generate Token **** : " + token);
			Cookie cookie = new Cookie(MyConstants.DEFAULT_TOKEN_NAME, token);
			log.debug("Write Token to Cookie and return to the Client : " + cookie.toString());
			response.addCookie(cookie);
			MyResponse myResponse = new MyResponse();
			return myResponse.success(userMessage.getUserName());
		}
	}

	/**
	 * 分页查询所有用户信息
	 * @param page
	 * @param rows
	 * @param userMessage
	 * @param response
	 * @throws IOException
	 * @return 返回用户列表
	 */
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public MyResponse userList(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "userName",required = false) String userName) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "userName", userName);
		MyResponse response = new MyResponse();
		Map<String,Object> resultData=new HashMap<String, Object>();	
		List<UserMessage> userList = userMessageService.getUserList(map);
		Long userTotal = userMessageService.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, userList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", userTotal);
		return response.success(resultData);
	}
	/**
	 * 添加用户
	 * @param userMessage
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/user",method=RequestMethod.POST,produces = PRODUCES)
	public MyResponse save(@RequestBody @Valid UserMessage userMessage) throws Exception{
		MyResponse myResponse=new MyResponse();
		int resultNum = 0;
		resultNum = userMessageService.add(userMessage);
		if(resultNum > 0) {
			myResponse.success();
		} else {
			myResponse.failure();
		}
		return myResponse;
	}
	
	/**
	 * 修改用户信息
	 * @param userMessage
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/user",method=RequestMethod.PUT,produces = PRODUCES)
	public MyResponse update(@RequestBody @Valid UserMessage userMessage) throws Exception {
		MyResponse response=new MyResponse();
		int resultNum = 0;
		resultNum = userMessageService.update(userMessage);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除用户信息
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/user",method=RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse delete(@RequestBody List<Integer> ids) throws IOException {
		if(ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
		//支持删除多条记录
		for(int i = 0;i < ids.size();i++) {
			userMessageService.delete(ids.get(i));
		}
		return new MyResponse().success();
	}
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public MyResponse logout(HttpServletRequest request){
		String token = request.getHeader(MyConstants.DEFAULT_TOKEN_NAME);
		System.out.println(token);
		tokenManager.deleteToken(token);
		log.debug("Logout Success...");
		return new MyResponse().success();
	}
}
