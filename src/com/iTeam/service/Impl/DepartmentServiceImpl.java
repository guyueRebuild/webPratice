package com.iTeam.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.DepartmentDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Department;
import com.iTeam.service.DepartmentService;
import com.iTeam.service.EmployeeService;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentDao departmentDao;
	
	@Resource
	private EmployeeService employeeService;
	@Override
	public List<Department> getDepartmentList(Map<String, Object> map) {
		return departmentDao.getDepartmentList(map);
	}

	@Override
	public long getTotalDepartment() {
		return departmentDao.getTotalDepartment();
	}

	@Override
	public int addDepartment(Department department) {
		return departmentDao.addDepartment(department);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentDao.updateDepartment(department);
	}

	@Override
	public int deleteDepartment(int deptId) {
		Integer o;
		try {
			o=Integer.valueOf(deptId);
		}catch(Exception e) {
			return 0;
		}
		//把员工表中的部门编号字段设置为未分配部门编号
		List<Integer> deptIds = new ArrayList<>();
		deptIds.add(o);
		try {
			employeeService.updateDeptIdToDefaultByDeptIds(deptIds);
			//删除该部门信息
			return departmentDao.deleteDepartment(deptId);
		}catch(Exception e){
			throw new SqlRollbackException();
		}
	}

	@Override
	public int deleteDepartmentBatch(List<Integer> deptIds) {
		if(deptIds.isEmpty()) {
			return 0;
		}
		try {
			//把员工表中的部门编号字段设置为未分配部门编号
			employeeService.updateDeptIdToDefaultByDeptIds(deptIds);
			//删除该部门信息
			return departmentDao.deleteDepartmentBatch(deptIds);
		}catch(Exception e) {
			throw new SqlRollbackException();
		}
	}

	@Override
	public int updateManagerIdToNull(int empId) {
		return departmentDao.updateManagerIdToNull(empId);
	}

	@Override
	public int updateManagerIdsToNull(List<Integer> empIds) {
		return departmentDao.updateManagerIdsToNull(empIds);
	}

}
