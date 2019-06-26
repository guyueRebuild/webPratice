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

import com.iTeam.model.Client;
import com.iTeam.model.Goods;
import com.iTeam.model.PageBean;
import com.iTeam.model.Sales;
import com.iTeam.response.MyResponse;
import com.iTeam.service.SalesService;
import com.iTeam.util.PageUtil;


import net.sf.json.JSONArray;

@RestController
@RequestMapping("/")
public class SalesController {
	@Resource
	private SalesService service;
	
	private final String PRODUCES = "application/json";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 查询商品销售信息
	 * @param page
	 * @param rows
	 * @param handler
	 * @return
	 */
	@RequestMapping(value = "/sales",method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "handler",required = false)String handler) {
		
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String,Object> map = PageUtil.getMapFromPage(pageBean, "handler", handler);
		MyResponse response = new MyResponse();
		Map<String,Object> resultData=new HashMap<String, Object>();		
		List<Sales> salesList = service.findAll(map);
		Long total = service.getTotal(map);
		
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, salesList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return response.success(resultData);
	}
	
	
	/**
	 * 查询货物列表
	 * @return
	 */
	@RequestMapping(value = "/sales/goods",method = RequestMethod.GET)
	public MyResponse findGoodsName() {
		List<Goods> goodsList = service.getGoodsList(null);
		MyResponse response = new MyResponse();
		return response.success(goodsList);
	}
	
	
	/**
	 * 查询客户列表
	 * @return
	 */
	@RequestMapping(value="/sales/clients",method = RequestMethod.GET)
	public MyResponse findClientName() {
		List<Client> lc = service.getClientList(null);
		MyResponse response = new MyResponse();
		return response.success(lc);
	}
	
	
	@RequestMapping(value = "/sales",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addSales(@RequestBody Sales sales) {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(sales);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	@RequestMapping(value = "/sales",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateSales(@RequestBody Sales sales){
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(sales);
		System.out.println(resultNum);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	@RequestMapping(value = "/sales",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteSales(@RequestBody List<Integer> ids){
		for(int i = 0;i < ids.size();i++) {
			service.deleteBysNo(ids.get(i));
		}
		return new MyResponse().success();
	}

}
