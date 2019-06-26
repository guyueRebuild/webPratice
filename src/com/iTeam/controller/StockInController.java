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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.PageBean;
import com.iTeam.model.StockIn;
import com.iTeam.model.Storage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.InventoryService;
import com.iTeam.service.StockInService;
import com.iTeam.service.StorageService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

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
	@RequestMapping(value = "/stockIns", method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "handler",required = false) String handler) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "handler", handler);
		Map<String,Object> resultData=new HashMap<String, Object>();		
		MyResponse response = new MyResponse();
		List<StockIn> stockInsList = service.findAll(map);
		int total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, stockInsList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		response.success(response);
		return response;
	}

	/**
	 * 添加入库信息
	 * 
	 * @param stockIn
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockIn", method = RequestMethod.POST, produces = PRODUCES)
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
	@RequestMapping(value = "/stockIn/{storageNoBefore}", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateStockIn(@RequestBody StockIn stockIn, @PathVariable("storageNoBefore") String storageNoBefore)
			throws IOException {
		// 修改前仓库编号
		int forward = Integer.parseInt(storageNoBefore);
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
	public MyResponse deleteByStockInNo(@RequestBody List<Integer> ids) throws Exception {
		for (int i = 0; i < ids.size(); i++) {
			int storageNo = service.getStorageByStockInNo(ids.get(i));
			service.deleteByStockInNo(ids.get(i));
			// 更新该仓库当前容量
			updateStorage(storageNo);
		}
		return new MyResponse().success();
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
