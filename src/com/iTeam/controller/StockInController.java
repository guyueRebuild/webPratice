package com.iTeam.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.StockIn;
import com.iTeam.model.Storage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.InventoryService;
import com.iTeam.service.StockInService;
import com.iTeam.service.StorageService;
import com.iTeam.util.DataJsonValueProcessor;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 入库信息控制层
 * 
 * @author 谭昌敏
 *
 */
@RestController
@RequestMapping("/")
public class StockInController {
	@Autowired
	private StockInService service;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private StorageService storageService;

	private final String PRODUCES = "application/json";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:���������ֵ��false:����Ϊ��ֵ
	}

	/**
	 * 显示入库信息
	 * 
	 * @param page
	 * @param rows
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockIns/{handler}/{page}/{rows}", method = RequestMethod.GET)
	public MyResponse list(@PathVariable("page") String page, @PathVariable("rows") String rows,
			@PathVariable("handler") String handler) throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();

		map.put("handler", StringUtil.formatString(handler));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<StockIn> stockInsList = service.findAll(map);
		int total = service.getTotal(map);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(stockInsList, jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		MyResponse response = new MyResponse();
		response.success(resultJsonObj);
		return response;
	}

	/**
	 * 添加入库信息
	 * 
	 * @param stockIn
	 * @throws IOException
	 */
	@RequestMapping(value = "/StockIn", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addStockIn(@RequestBody StockIn stockIn) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.addStockIn(stockIn);
		if (resultNum > 0) {
			// 更新该仓库当前容量
			updateStorage(stockIn.getStorageNo());
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改入库信息
	 * 
	 * @param stockIn
	 * @throws IOException
	 */
	@RequestMapping(value = "/StockIn/{stockInNo}", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateStockIn(@RequestBody StockIn stockIn, @PathVariable("stockInNo") String stockInNo)
			throws IOException {
		// 修改前仓库编号
		int forward = Integer.parseInt(stockInNo);
		// 修改后仓库编号
		int after = stockIn.getStorageNo();

		MyResponse response = new MyResponse();
		int resultNum = 0;
		//// 更新入库单
		resultNum = service.updateStockIn(stockIn);
		if (resultNum > 0) {
			// 如果修改了仓库编号，同时更新原来仓库与后来仓库
			if (after != forward) {
				updateStorage(forward);
			}
			updateStorage(after);
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 删除入库信息
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stockIn", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteByStockInNo(@RequestBody String ids) throws Exception {
		String substring = ids.substring(1, ids.length()-1);
		String[] idsStr = substring.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			int storageNo = service.getStorageByStockInNo(Integer.parseInt(idsStr[i]));
			service.deleteByStockInNo(Integer.parseInt(idsStr[i]));
			// 更新该仓库当前容量
			updateStorage(storageNo);
		}
		MyResponse response = new MyResponse();
		return response.success();
	}

	public void updateStorage(int storageNo) {
		int in = inventoryService.getStorageInInventory(storageNo);
		int out = inventoryService.getStorageOutInventory(storageNo);

		Storage storage = new Storage();
		storage.setStorageNo(storageNo);
		storage.setCurrentStorage(in - out);
		storageService.updateCurrentStorage(storage);
	}
}
