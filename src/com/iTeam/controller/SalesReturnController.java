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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.SalesReturn;
import com.iTeam.response.MyResponse;
import com.iTeam.service.SalesReturnService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;



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
	@RequestMapping(value = "/salesReturn",method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "handler",required = false)String handler) {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String,Object> map = PageUtil.getMapFromPage(pageBean, "handler", handler);
		Map<String,Object> resultData=new HashMap<String,Object>();
		MyResponse response = new MyResponse();
		List<SalesReturn> salesReturnList = service.findAll(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, salesReturnList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return response.success(resultData);
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
	public MyResponse deleteSalesReturn(@RequestBody List<Integer> ids){
		for(int i = 0;i < ids.size();i++) {
			service.deleteBysNo(ids.get(i));
		}
		return new MyResponse().success();
	}
}
