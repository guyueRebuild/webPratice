package com.iTeam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.Client;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.ClientService;
import com.iTeam.util.DataJsonValueProcessor;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 控制器类
 * @author LMH
 *
 */

@RestController
@RequestMapping("/")
public class ClientController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private ClientService service;
	
	private final String PRODUCES = "application/json";
	
	/**
	 * 分页查询客户信息
	 * @param page
	 * @param rows
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client/{client}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse clientList(@PathVariable("page")String page,@PathVariable("rows")String rows,@PathVariable("client") String client) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		
		map.put("client", StringUtil.formatString(client));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Client> clientList = service.getClientList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(clientList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return new MyResponse().success(resultJsonObj);
	}
	
	/**
	 * 添加客户信息
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addClient(@RequestBody Client client) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(client);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改客户信息
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateClient(@RequestBody Client client) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(client);
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
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/client",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteClient(@RequestBody String ids)throws Exception{
		String substring = ids.substring(1, ids.length()-1);
		String []idsStr=substring.split(",");
		for(int i=0;i<idsStr.length;i++){
			service.delete(Integer.parseInt(idsStr[i]));
		}
		return new MyResponse().success();
	}
}
