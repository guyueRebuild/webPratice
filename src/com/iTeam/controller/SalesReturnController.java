package com.iTeam.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.SalesReturn;
import com.iTeam.response.MyResponse;
import com.iTeam.service.SalesReturnService;
import com.iTeam.util.DataJsonValueProcessor;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@RestController
@RequestMapping("/")
public class SalesReturnController {
	@Resource
	private SalesReturnService service;
	
	private final String PRODUCES = "application/json";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 分页查询销售退货信息
	 * @param page
	 * @param rows
	 * @param handler
	 * @return
	 */
	@RequestMapping(value = "/salesReturn/{handler}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse list(@PathVariable("pages") String page,@PathVariable("rows") String rows,@PathVariable("handler") String handler) {
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		MyResponse response = new MyResponse();
		map.put("handler", StringUtil.formatString(handler));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<SalesReturn> salesReturnList = service.findAll(map);
		Long total = service.getTotal(map);
		
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(salesReturnList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return response.success(resultJsonObj);
	}
	
	/**
	 * 添加退货信息
	 * @param sr
	 * @return
	 */
	@RequestMapping(value = "/salesReturn",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addReturnSales(@RequestBody SalesReturn sr) {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(sr);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改销售回退信息
	 * @param sr
	 * @return
	 */
	@RequestMapping(value = "/salesReturn",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateSalesReturn(@RequestBody SalesReturn sr){
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(sr);
		System.out.println(resultNum);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除销售回退信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/salesReturn",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteSalesReturn(@RequestBody String ids){
		String substring = ids.substring(1, ids.length()-1);
		MyResponse response = new MyResponse();
		String[] sNo = substring.split(",");
		for(int i = 0;i < sNo.length;i++) {
			service.deleteBysNo(Integer.parseInt(sNo[i]));
		}
		return response.success();
	}
}
