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
import com.iTeam.model.StockOut;
import com.iTeam.model.Storage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.InventoryService;
import com.iTeam.service.StockOutService;
import com.iTeam.service.StorageService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

/**
 * 出库信息控制层
 * 
 * @author 谭昌敏
 *
 */

@RestController
@RequestMapping("/")
public class StockOutController {
	@Autowired
	private StockOutService stockOutService;

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
	 * 显示出库信息
	 * 
	 * @param page
	 * @param rows
	 * @param stockOut
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockOuts", method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "handler",required = false) String handler) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "handler", handler);
		MyResponse response = new MyResponse();
		Map<String,Object> resultData=new HashMap<String, Object>();		
		List<StockOut> stockOutsList = stockOutService.findAll(map);
		int total = stockOutService.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, stockOutsList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("rows", jsonArray);
		resultData.put("total", total);
		return response.success(resultData);
	}

	/**
	 * 添加出库信息
	 * 
	 * @param stockOut
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockOut", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addStockOut(@RequestBody StockOut stockOut) throws IOException {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		// 增加出库单
		resultNum = stockOutService.addStockOut(stockOut);
		if (resultNum > 0) {
			// 更新该仓库当前容量
			updateStorage(stockOut.getStorageNo());
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改出库信息
	 * 
	 * @param stockOut
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockOut/{storageNoBefore}", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateStockOut(@RequestBody StockOut stockOut,
			@PathVariable("storageNoBefore") String storageNoBefore) throws IOException {
		MyResponse response = new MyResponse();
		// 修改前出库仓库编号
		int forward = Integer.parseInt(storageNoBefore);
		// 修改后出库仓库编号
		int after = stockOut.getStorageNo();
		int resultNum = 0;
		// 更新出库单
		resultNum = stockOutService.updateStockOut(stockOut);
		if (resultNum > 0) {
			// 如果修改了出库仓库，同时更新原来仓库与后来仓库
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
	 * 删除出库信息
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stockOut", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteByStockOutNo(@RequestBody List<Integer> ids) throws Exception {
		for (int i = 0; i < ids.size(); i++) {
			int storageNo = stockOutService.getStorageByStockOutNo(ids.get(i));
			stockOutService.deleteByStockOutNo(ids.get(i));
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
