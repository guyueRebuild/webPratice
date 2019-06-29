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
import com.iTeam.model.Storage;
import com.iTeam.response.MyResponse;
import com.iTeam.service.StorageService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/")
public class StorageController {
	
	// 在mvc的控制层整合service服务层
	@Resource
	private StorageService service;
	
	private final String PRODUCES = "application/json";
	
	/**
	 * 查询仓库信息
	 * @param page
	 * @param rows
	 * @param storageName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/storages",method=RequestMethod.GET)
	public MyResponse storageList(@RequestParam(value = "page",required = false) String page,
			@RequestParam(value = "rows",required = false)String rows,
			@RequestParam(value = "storageName",required = false)String storageName) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String,Object> map = PageUtil.getMapFromPage(pageBean, "storageName", storageName);
		Map<String,Object> resultData=new HashMap<String, Object>();			
		List<Storage> storageList = service.getStorageList(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, storageList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}
	
	@RequestMapping(value="/allstorages",method=RequestMethod.GET)
	public MyResponse nameList() throws IOException {
		List<Storage> list = service.getStorageList(null);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return new MyResponse().success(jsonArray);
	}
	
	/**
	 * 提交仓库信息
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/storage",method=RequestMethod.POST,produces=PRODUCES)
	public MyResponse addStorage(@RequestBody Storage storage) throws Exception {
		MyResponse response=new MyResponse();
		int resultNum = 0;
		resultNum = service.add(storage);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改仓库信息
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/storage",method=RequestMethod.PUT,produces=PRODUCES)
	public MyResponse updateStorage(@RequestBody Storage storage) throws Exception {
		MyResponse response=new MyResponse();
		int resultNum = 0;
		resultNum = service.update(storage);
		if(resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}	
	
	/**
	 * 删除仓库信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/storage",method=RequestMethod.DELETE,produces=PRODUCES)
	public MyResponse deleteStorage(@RequestBody List<Integer> ids)throws Exception{
		if(ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
//		for(int i=0;i<ids.size();i++){
//			service.delete(ids.get(i));
//			System.out.println(ids.get(i));
//		}
		service.deleteBatch(ids);
		return new MyResponse().success();
	}
}
