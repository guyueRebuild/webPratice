package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployeeList(Map<String, Object> map);
	
	public List<Employee> getEmployeeListByDeptId(Map<String, Object> map);
	
	public long getTotalEmployee();
	
	public int addEmployee(Employee employee) throws Exception;
	
	public int updateEmployee(Employee employee) throws Exception;
	
	public int updateDeptIdToDefaultByDeptIds(List<Integer> deptIds);
	
	public int deleteEmployee(int empId);
	
	public int deleteEmployeeBatch(List<Integer> empIds);
}
