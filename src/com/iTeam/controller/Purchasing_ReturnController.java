package com.iTeam.controller;

import java.io.IOException;
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
import com.iTeam.model.Purchasing_Return;
import com.iTeam.response.MyResponse;
import com.iTeam.service.Purchasing_ReturnService;
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
public class Purchasing_ReturnController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private Purchasing_ReturnService service;
	
	private final String PRODUCES = "application/json";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}	
	
	/**
	 * 分页查询采购退货信息
	 * @param page
	 * @param rows
	 * @param goodsName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasing_Returns/{goodsName}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse purchasing_ReturnList(@PathVariable("page") String page,@PathVariable("rows")String rows,@PathVariable("goodsName")String goodsName) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		MyResponse response = new MyResponse();
		
		map.put("goodsName",StringUtil.formatString(goodsName));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Purchasing_Return> List = service.getPurchasing_ReturnList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(List, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return response.success(resultJsonObj);
	}
	
	/**
	 * 提交商品信息
	 * @param purchasing_Return
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasing_Return",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addPurchasing_Return(@RequestBody Purchasing_Return purchasing_Return) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(purchasing_Return);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改采购退货信息
	 * @param purchasing_Return
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasing_Return",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updatePurchasing_Return(@RequestBody Purchasing_Return purchasing_Return) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(purchasing_Return);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}	
	
	//在浏览中通过URL调用deletePurchasing_Return这个方法		
	@RequestMapping(value = "/purchasing_Return",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deletePurchasing_Return(@RequestBody String ids)throws Exception{
		String substring = ids.substring(1, ids.length()-1);
		String []idsStr=substring.split(",");
		for(int i=0;i<idsStr.length;i++){
			service.delete(Integer.parseInt(idsStr[i]));
		}
		return new MyResponse().success();
	}
}
