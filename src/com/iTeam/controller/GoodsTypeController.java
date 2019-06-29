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

import com.iTeam.model.GoodsType;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.GoodsTypeService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

/**
 * 商品类别控制层
 * 
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
	 * 
	 * @param page
	 * @param rows
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsTypes", method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "type", required = false) String goodsType) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "type", goodsType);
		Map<String, Object> resultData = new HashMap<String, Object>();
		List<GoodsType> goodsTypelist = service.list(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, goodsTypelist, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}

	/**
	 * 查询类别名称
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/allGoodsType", method = RequestMethod.GET)
	public MyResponse typeList() throws IOException {
		List<GoodsType> typeList = service.list(null);
		return new MyResponse().success(typeList);
	}

	/**
	 * 添加商品类型
	 * 
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addType(@RequestBody GoodsType goodsType) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(goodsType);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改商品类型
	 * 
	 * @param goodsType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateType(@RequestBody GoodsType goodsType) throws Exception {
		MyResponse response = new MyResponse();
		int resltNum = 0;
		resltNum = service.update(goodsType);
		if (resltNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 删除商品类型
	 * 
	 * @param ids
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsType", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteType(@RequestBody List<Integer> ids) throws IOException {
		if (ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
//		for (int i = 0; i < ids.size(); i++) {
//			service.delete(ids.get(i));
//		}
		service.deleteBatch(ids);
		return new MyResponse().success();
	}
}
