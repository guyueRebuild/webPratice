package com.iTeam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iTeam.model.Employee;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.EmployeeService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/")
public class EmployeeController {

	@Resource
	private EmployeeService employeeService;

	private final String PRODUCES = "application/json";

	/**
	 * *查询用户信息
	 * 
	 * @param page
	 * @param rows
	 * @param empName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public MyResponse getEmployeeList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "empName", required = false) String empName) throws Exception {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "empName", empName);
		List<Employee> employeeList = employeeService.getEmployeeList(map);
		long total = employeeService.getTotalEmployee();
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, employeeList, "yyyy-MM-dd HH:mm:ss");
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("total", total);
		resultData.put("list", jsonArray);
		return new MyResponse().success(resultData);
	}

	/**
	 * *增加用户信息
	 * 
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addEmployee(@RequestBody Employee employee) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = employeeService.addEmployee(employee);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * *修改用户信息
	 * 
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateEmployee(@RequestBody Employee employee) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		System.out.println(employee);
		resultNum = employeeService.updateEmployee(employee);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * *删除用户信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteEmployee(@RequestBody List<Integer> ids) throws Exception {
		if (ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
		employeeService.deleteEmployeeBatch(ids);
		return new MyResponse().success();
	}
}
