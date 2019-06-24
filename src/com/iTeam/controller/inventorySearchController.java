package com.iTeam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.Inventory;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.InventoryService;
import com.iTeam.util.DataJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/")
public class inventorySearchController {
	
	@Autowired
	private InventoryService service;
	
	/**
	 * 根据仓库编号查询该仓库中所有商品的库存数量
	 * @param page
	 * @param rows
	 * @param inventoryStorageNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/inventorySearch/{inventoryStorageNo}/{page}/{rows}",method = RequestMethod.GET)
	public MyResponse list(@PathVariable("page") String page,
			@PathVariable("rows") String rows,@PathVariable("inventoryStorageNo")String inventoryStorageNo)
			throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject resultJsonObj = new JSONObject();
		int storageNo = Integer.parseInt(inventoryStorageNo);
		
		map.put("stroageNo", storageNo);;
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		//查询每种商品在指定编号的仓库的进货数量
		List<Inventory> in = service.getStockInInventory(storageNo);
		//查询每种商品在指定编号的仓库的出货数量
		List<Inventory> out = service.getStockOutInventory(storageNo);
		//得到每种商品在指定仓库的库存数量
		for(Inventory inInventory : in){
			for(Inventory outInventory : out){
				if(inInventory.getGoodsName().equals(outInventory.getGoodsName())){
					inInventory.setquantity(inInventory.getquantity()-outInventory.getquantity());
				}
			}
		}
		int total = in.size()>out.size()?in.size():out.size();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DataJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		//JSONArray jsonArray = JSONArray.fromObject(in, jsonConfig);
		JSONArray jsonArray = JSONArray.fromObject(in.subList((Integer.parseInt(page)-1)*Integer.parseInt(rows), 
				total>(Integer.parseInt(page))*Integer.parseInt(rows)?Integer.parseInt(rows):Integer.parseInt(rows)*(Integer.parseInt(page)-1)+total-(Integer.parseInt(page)-1)*Integer.parseInt(rows)), 
				jsonConfig);
		resultJsonObj.put("rows", jsonArray);
		resultJsonObj.put("total", total);
		return new MyResponse().success(resultJsonObj);
	}
}
