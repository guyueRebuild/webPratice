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

import com.iTeam.model.Goods;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.GoodsService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

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
	@RequestMapping(value = "/goods",method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "goodsName",required = false)String goodsName) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String,Object> map = PageUtil.getMapFromPage(pageBean, "goodsName", goodsName);
		Map<String,Object> resultData=new HashMap<String, Object>();
		List<Goods> goodsList = service.getGoodsList(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, goodsList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}
	
	/**
	 * 所有获取商品名称
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/allGoods",method = RequestMethod.GET,produces = PRODUCES)
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
	public MyResponse deleteGoods(@RequestBody List<Integer> ids) throws IOException {
		for(int i = 0;i < ids.size();i++) {
			service.delete(ids.get(i));
		}
		return new MyResponse().success();
	}
}
