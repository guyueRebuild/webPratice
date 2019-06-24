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

import com.iTeam.model.GoodsType;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.GoodsTypeService;
import com.iTeam.util.DataJsonValueProcessor;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 商品类别控制层
 * @author XieZhiHao
 *
 */
@RestController
@RequestMapping("/")
public class GoodsTypeController {

	@Resource
	private GoodsTypeService service;
	
	private final String PRODUCES = "application/json";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	/**
	 * 分页查询商品类型信息
	 * @param page
	 * @param rows
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsTypes/{goodsType}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse list(@PathVariable("page") String page,@PathVariable("rows")String rows,@PathVariable("goodsType") String goodsType) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		
		map.put("type", StringUtil.formatString(goodsType));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<GoodsType> list = service.list(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return new MyResponse().success(resultJsonObj);
	}
	
	/**
	 * 查询类别名称
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsTypes",method = RequestMethod.GET)
	public MyResponse typeList() throws IOException {
		List<GoodsType> typeList = service.list(null);
		return new MyResponse().success(typeList);
	}
	
	
	/**
	 * 添加商品类型
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addType(@RequestBody GoodsType goodsType) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(goodsType);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改商品类型
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateType(GoodsType goodsType) throws IOException {
		MyResponse response = new MyResponse();
		int resltNum = 0;
		resltNum = service.update(goodsType);
		if(resltNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除商品类型
	 * @param ids
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteType(@RequestBody String ids) throws IOException {
		String substring = ids.substring(1, ids.length()-1);
		String[] typeNo = substring.split(",");
		for(int i = 0;i < typeNo.length;i++) {
			service.delete(Integer.parseInt(typeNo[i]));
		}
		return new MyResponse().success();
	}
}
