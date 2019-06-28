package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployeeList(Map<String, Object> map);
	
	public List<Employee> getEmployeeListByDeptId(Map<String, Object> map);
	
	public long getTotalEmployee();
	
	public int addEmployee(Employee employee);
	
	public int updateEmployee(Employee employee);
	
	public int updateDeptIdToDefaultByDeptIds(List<Integer> deptIds);
	
	public int deleteEmployee(int empId);
	
	public int deleteEmployeeBatch(List<Integer> empIds);
}
