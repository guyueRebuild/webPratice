package com.iTeam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.Inventory;
import com.iTeam.response.MyResponse;
import com.iTeam.service.InventoryService;
import com.iTeam.util.PageUtil;
import com.iTeam.util.StringUtil;

import net.sf.json.JSONArray;

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
	@RequestMapping(value = "/inventorySearch",method = RequestMethod.GET)
	public MyResponse list(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "inventoryStorageNo",required = true)String inventoryStorageNo)
			throws IOException {
		
		int storageNo = StringUtil.isEmpty(inventoryStorageNo)?null:Integer.parseInt(inventoryStorageNo);
		Map<String,Object> resultData=new HashMap<String, Object>();
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
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, in.subList((Integer.parseInt(page)-1)*Integer.parseInt(rows), 
				total>(Integer.parseInt(page))*Integer.parseInt(rows)?Integer.parseInt(rows):Integer.parseInt(rows)*(Integer.parseInt(page)-1)+total-(Integer.parseInt(page)-1)*Integer.parseInt(rows)),
						"yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}
}
