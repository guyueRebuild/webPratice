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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.Purchasing;
import com.iTeam.response.MyResponse;
import com.iTeam.service.PurchasingService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

/**
 * 控制器类
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
	@RequestMapping(value = "/purchasings",method = RequestMethod.GET)
	public MyResponse purchasingList(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "goodsName",required = false)String goodsName) throws IOException {
		
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String,Object> map = PageUtil.getMapFromPage(pageBean, "goodsName", goodsName);
		MyResponse response = new MyResponse();
		Map<String,Object> resultData=new HashMap<String, Object>();		
		List<Purchasing> purchasingList = service.getPurchasingList(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, purchasingList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return response.success(resultData);
	}
	
	/**
	 * 添加采购信息
	 * @param purchasing
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/purchasing",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addPurchasing(@RequestBody Purchasing purchasing) throws Exception {
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/purchasing",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updatePurchasing(@RequestBody Purchasing purchasing) throws Exception {
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
	
	/**
	 * 刪除采购信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/purchasing",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deletePurchasing(@RequestBody List<Integer> ids)throws Exception{
		if(ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
//		for(int i=0;i<ids.size();i++){
//			service.delete(ids.get(i));
//		}
		service.deleteBatch(ids);
		return new MyResponse().success();
	}
}
