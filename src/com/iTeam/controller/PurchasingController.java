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
import com.iTeam.model.Purchasing;
import com.iTeam.response.MyResponse;
import com.iTeam.service.PurchasingService;
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
public class PurchasingController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private PurchasingService service;
	private final String PRODUCES = "application/json";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 分页查询采购信息
	 * @param page
	 * @param rows
	 * @param goodsName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasings/{goodsName}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse purchasingList(@PathVariable("page") String page,@PathVariable("rows") String rows,@PathVariable("goodsName") String goodsName) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		MyResponse response = new MyResponse();
		
		map.put("goodsName",StringUtil.formatString(goodsName));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Purchasing> purchasingList = service.getPurchasingList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(purchasingList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return response.success(resultJsonObj);
	}
	
	/**
	 * 添加采购信息
	 * @param purchasing
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasing",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addPurchasing(@RequestBody Purchasing purchasing) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(purchasing);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 更新采购信息
	 * @param purchasing
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/purchasing",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updatePurchasing(@RequestBody Purchasing purchasing) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(purchasing);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}	
	
	//在浏览中通过URL调用deletePurchasing这个方法		
	@RequestMapping(value = "/purchasing",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deletePurchasing(@RequestBody String ids)throws Exception{
		String substring = ids.substring(1, ids.length()-1);
		String []idsStr=substring.split(",");
		for(int i=0;i<idsStr.length;i++){
			service.delete(Integer.parseInt(idsStr[i]));
		}
		MyResponse response = new MyResponse();
		return response.success();
	}
}
