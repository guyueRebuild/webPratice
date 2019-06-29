package com.iTeam.service;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Department;

public interface DepartmentService {
	
	public List<Department> getDepartmentList(Map<String, Object> map);
	
	public long getTotalDepartment();
	
	public int addDepartment(Department department) throws Exception;
	
	public int updateDepartment(Department department) throws Exception;
	
	public int deleteDepartment(int deptId);
	
	public int deleteDepartmentBatch(List<Integer> deptIds);
	
	public int updateManagerIdToNull(int empId);
	
	public int updateManagerIdsToNull(List<Integer> empIds);
}
