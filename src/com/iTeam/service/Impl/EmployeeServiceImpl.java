package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.EmployeeDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Employee;
import com.iTeam.service.DepartmentService;
import com.iTeam.service.EmployeeService;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private DepartmentService departmentService;
	
	@Override
	public List<Employee> getEmployeeList(Map<String, Object> map) {
		return employeeDao.getEmployeeList(map);
	}

	@Override
	public long getTotalEmployee() {
		return employeeDao.getTotalEmployee();
	}

	@Override
	public int addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public int updateEmployee(Employee employee) {
		//如果该员工是部门经理,把部门表中的经理编号字段设置为空
		try {
			departmentService.updateManagerIdToNull(employee.getEmpId());
			return employeeDao.updateEmployee(employee);
		}catch(Exception e) {
			throw new SqlRollbackException();
		}
	}

	@Override
	public int deleteEmployee(int empId) {
		try {
			//更新部门表中的经理字段
			departmentService.updateManagerIdToNull(empId);
			//删除该员工
			return employeeDao.deleteEmployee(empId);
		}catch(Exception e) {
			throw new SqlRollbackException();
		}	
	}

	@Override
	public int deleteEmployeeBatch(List<Integer> empIds) {
		try {
			// 更新部门表中的经理字段
			departmentService.updateManagerIdsToNull(empIds);
			// 删除员工
			return employeeDao.deleteEmployeeBatch(empIds);
		}catch(Exception e) {
			throw new SqlRollbackException();
		}
	}

	@Override
	public List<Employee> getEmployeeListByDeptId(Map<String, Object> map) {
		return employeeDao.getEmployeeListByDeptId(map);
	}

	@Override
	public int updateDeptIdToDefaultByDeptIds(List<Integer> deptIds) {
		return employeeDao.updateDeptIdToDefaultByDeptIds(deptIds);
	}

}
