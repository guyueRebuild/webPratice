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

import com.iTeam.model.Department;
import com.iTeam.model.Employee;
import com.iTeam.model.PageBean;
import com.iTeam.response.MyResponse;
import com.iTeam.service.DepartmentService;
import com.iTeam.service.EmployeeService;
import com.iTeam.util.PageUtil;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/")
public class DepartmentController {

	@Resource
	private DepartmentService departmentService;

	@Resource
	private EmployeeService employeeService;

	private final String PRODUCES = "application/json";

	/**
	 * *查询部门信息
	 * 
	 * @param page
	 * @param rows
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public MyResponse getDepartmentList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "deptName", required = false) String deptName) throws Exception {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "deptName", deptName);
		List<Department> employeeList = departmentService.getDepartmentList(map);
		long total = departmentService.getTotalDepartment();
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, employeeList, "yyyy-MM-dd HH:mm:ss");
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("total", total);
		resultData.put("list", jsonArray);
		return new MyResponse().success(resultData);
	}

	/**
	 * *根据部门编号查询员工信息
	 * 
	 * @param page
	 * @param rows
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/department/employees", method = RequestMethod.GET)
	public MyResponse getDepartmentsByDeptId(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "deptId", required = true) Integer deptId) {
		PageBean pageBean = PageUtil.getDefaultPage(rows, page);
		Map<String, Object> map = PageUtil.getMapFromPage(pageBean, "deptId", deptId);
		List<Employee> list = employeeService.getEmployeeListByDeptId(map);
		JSONArray jsonArray = PageUtil.ProcessDataJsonValue(java.util.Date.class, list, "yyyy-MM-dd HH:mm:ss");
		return new MyResponse().success(jsonArray);
	}

	/**
	 * *增加部门信息
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/department", method = RequestMethod.POST, produces = PRODUCES)
	public MyResponse addDepartment(@RequestBody Department department) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = departmentService.addDepartment(department);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * *修改部门信息
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/department", method = RequestMethod.PUT, produces = PRODUCES)
	public MyResponse updateDepartment(@RequestBody Department department) throws Exception {
		MyResponse response = new MyResponse();
		int resultNum = 0;
		resultNum = departmentService.updateDepartment(department);
		if (resultNum > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * *删除部门信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/department", method = RequestMethod.DELETE, produces = PRODUCES)
	public MyResponse deleteDepartment(@RequestBody List<Integer> ids) throws Exception {
		if (ids.isEmpty())
			return new MyResponse().failure("要删除的数目为零");
		departmentService.deleteDepartmentBatch(ids);
		return new MyResponse().success();
	}

}
