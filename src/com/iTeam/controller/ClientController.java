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

import com.iTeam.model.Client;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.ClientService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

/**
 * 控制器类
 * 
 * @author LMH
 *
 */

@RestController
@RequestMapping("/")
public class ClientController {

	// 在mvc的控制层整合service服务层
	@Resource
	private ClientService service;

	private final String PRODUCES = "application/json";

	/**
	 * 分页查询客户信息
	 * 
	 * @param page
	 * @param rows
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public MyResponse clientList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "client", required = false) String client) throws IOException {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "client", client);
		Map<String, Object> resultData = new HashMap<String, Object>();
		List<Client> clientList = service.getClientList(map);
		Long total = service.getTotal(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, clientList, "yyyy-MM-dd HH:mm:ss");
		resultData.put("list", jsonArray);
		resultData.put("total", total);
		return new MyResponse().success(resultData);
	}

	/**
	 * 添加客户信息
	 * 
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addClient(@RequestBody Client client) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.add(client);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;

	}

	/**
	 * 修改客户信息
	 * 
	 * @param client
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/client", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateClient(@RequestBody Client client) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = service.update(client);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;

	}

	/**
	 * 删除用户信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/client", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteClient(@RequestBody List<Integer> ids) throws Exception {
		if(ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
		for (int i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i));
			service.delete(ids.get(i));
		}
		return new MyResponse().success();
	}
}
