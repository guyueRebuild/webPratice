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

import com.iTeam.model.PageBean;
import com.iTeam.model.Provider;
import com.iTeam.response.MyResponse;
import com.iTeam.service.ProviderService;
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
public class ProviderController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private ProviderService service;
	
	private final String PRODUCES = "application/json";

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param provider
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/providers/{provider}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse providerList(@PathVariable("page") String page,
			@PathVariable("rows") String rows, @PathVariable("provider") String provider)
			throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		
		map.put("provider", StringUtil.formatString(provider));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Provider> providerList = service.getProviderList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(providerList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return new MyResponse().success(resultJsonObj);
	}
	
	/**
	 * 获取供应商名称列表
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/providers",method = RequestMethod.GET)
	public MyResponse list() throws IOException {
		List<Provider> list = service.getProviderList(null);
		return new MyResponse().success(list);
	}

	/**
	 * 添加供应商信息
	 * @param provider
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/provider",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addProvider(@RequestBody Provider provider) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(provider);;
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改供应商信息
	 * @param provider
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/provider",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateProvider(@RequestBody Provider provider) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(provider);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	//在浏览中通过URL调用deleteProvider这个方法	
	@RequestMapping(value = "/provider",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteProvider(@RequestBody String ids)throws Exception{
		String substring = ids.substring(1, ids.length()-1);
		String []idsStr=substring.split(",");
		for(int i=0;i<idsStr.length;i++){
			service.delete(Integer.parseInt(idsStr[i]));
		}
		return new MyResponse().success();
	}
}
