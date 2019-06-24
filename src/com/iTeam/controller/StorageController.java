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
import com.iTeam.model.Storage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.StorageService;
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
public class StorageController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private StorageService service;
	
	private final String PRODUCES = "application/json";
	
	//在浏览中通过URL调用storageList这个方法
	@RequestMapping(value="/storages/{storageName}/{page}/{rows}",method=RequestMethod.GET)
	public MyResponse storageList(@PathVariable("rows") String rows,@PathVariable("rows") String page,@PathVariable("storageName")String storageName) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		map.put("storage", StringUtil.formatString(storageName));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Storage> storageList = service.getStorageList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(storageList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		MyResponse response=new MyResponse();
		return response.success(resultJsonObj);
	}
	
	@RequestMapping(value="/storages",method=RequestMethod.GET)
	public MyResponse nameList() throws IOException {
		List<Storage> list = service.getStorageList(null);
		JSONArray jsonArray = JSONArray.fromObject(list);
		MyResponse response=new MyResponse();
		return response.success(jsonArray);
	}
	
	//在浏览中通过URL调用addStorage这个方法	
	@RequestMapping(value="/storage",method=RequestMethod.POST,produces=PRODUCES)
	public MyResponse addStorage(@RequestBody Storage storage) throws IOException {
		MyResponse response=new MyResponse();
		int resultNum = 0;
		resultNum = service.add(storage);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	//在浏览中通过URL调用updateStorage这个方法	
	@RequestMapping(value="/storage",method=RequestMethod.PUT,produces=PRODUCES)
	public MyResponse updateStorage(@RequestBody Storage storage) throws IOException {
		MyResponse response=new MyResponse();
		int resultNum = 0;
		resultNum = service.update(storage);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}	
	
	//在浏览中通过URL调用deleteStorage这个方法
	@RequestMapping(value="/storage",method=RequestMethod.DELETE,produces=PRODUCES)
	public MyResponse deleteStorage(@RequestBody String ids)throws Exception{
		String substring = ids.substring(1, ids.length()-1);
		String []idsStr=substring.split(",");
		for(int i=0;i<idsStr.length;i++){
			service.delete(Integer.parseInt(idsStr[i]));
			System.out.println(idsStr[i]);
		}
		MyResponse response=new MyResponse();
		return response.success();
	}
}
