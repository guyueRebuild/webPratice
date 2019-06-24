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

import com.iTeam.model.Goods;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.GoodsService;
import com.iTeam.util.DataJsonValueProcessor;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 商品信息控制器
 * @author XieZhiHao
 *
 */
@RestController
@RequestMapping("/")
public class GoodsController {
	
	@Resource
	private GoodsService service;
	
	private final String PRODUCES = "application/json";

	/**
	 *  获取商品信息列表
	 * @param page
	 * @param rows
	 * @param goodName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods/{goodsName}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse list(@PathVariable("page")String page,@PathVariable("rows")String rows,@PathVariable("goodsName")String goodsName) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		
		map.put("goodsName", StringUtil.formatString(goodsName));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Goods> goodsList = service.getGoodsList(map);
		Long total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(goodsList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return new MyResponse().success(resultJsonObj);
	}
	
	/**
	 * 所有获取商品名称
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods",method = RequestMethod.GET,produces = PRODUCES)
	public MyResponse goodsNameList() throws IOException {
		List<Goods> list = service.getGoodsList(null);
		return new MyResponse().success(list);
	}
	
	/**
	 * 添加商品信息
	 * @param goods
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods",method = RequestMethod.POST,produces = PRODUCES)
	public MyResponse addGoods(@RequestBody Goods goods) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(goods);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods",method = RequestMethod.PUT,produces = PRODUCES)
	public MyResponse updateGoods(@RequestBody Goods goods) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(goods);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除商品信息
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods",method = RequestMethod.DELETE,produces = PRODUCES)
	public MyResponse deleteGoods(@RequestBody String ids) throws IOException {
		String substring = ids.substring(1, ids.length()-1);
		String[] goodsNo = substring.split(",");
		for(int i = 0;i < goodsNo.length;i++) {
			service.delete(Integer.parseInt(goodsNo[i]));
		}
		return new MyResponse().success();
	}
}
