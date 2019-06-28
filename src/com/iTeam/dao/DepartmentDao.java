package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Department;

public interface DepartmentDao {
	
	public List<Department> getDepartmentList(Map<String, Object> map);
		
	public long getTotalDepartment();
	
	public int addDepartment(Department department);
	
	public int updateDepartment(Department department);
	
	public int deleteDepartment(int deptId);
	
	public int deleteDepartmentBatch(List<Integer> deptIds);
	
	public int updateManagerIdToNull(int empId);
	
	public int updateManagerIdsToNull(List<Integer> empIds);
	
	
}
