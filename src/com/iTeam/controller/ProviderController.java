package com.iTeam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.Provider;
import com.iTeam.response.MyResponse;
import com.iTeam.service.ProviderService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

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
	@RequestMapping(value = "/providers",method = RequestMethod.GET)
	public MyResponse providerList(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "provider",required = false) String provider)
			throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "provider", provider);
		List<Provider> providerList = service.getProviderList(map);
		Map<String,Object> resultData=new HashMap<String, Object>();
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, providerList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}
	
	/**
	 * 获取供应商名称列表
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/allProviders",method = RequestMethod.GET)
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
	public MyResponse addProvider(@RequestBody Provider provider) throws Exception {
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
	public MyResponse updateProvider(@RequestBody Provider provider) throws Exception {
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

	/**
	 * 删除供应商信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/provider",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteProvider(@RequestBody List<Integer> ids)throws Exception{
		for(int i=0;i<ids.size();i++){
			service.delete(ids.get(i));
		}
		return new MyResponse().success();
	}
}
