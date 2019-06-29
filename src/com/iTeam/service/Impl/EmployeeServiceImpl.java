package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.EmployeeDao;
import com.iTeam.exception.DuplicationException;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Employee;
import com.iTeam.service.DepartmentService;
import com.iTeam.service.EmployeeService;
import com.iTeam.util.StringUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	public int addEmployee(Employee employee) throws Exception {
		if(StringUtil.isEmpty(employee.getEmpPhone())&&employeeDao.getEmpPhone(employee.getEmpPhone())!=0) {
			throw new DuplicationException("该手机号码已经被占用");
		}
		try {
			return employeeDao.addEmployee(employee);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws Exception {
		if(StringUtil.isEmpty(employee.getEmpPhone())&&employeeDao.getEmpPhone(employee.getEmpPhone())!=0) {
			throw new DuplicationException("该手机号码已经被占用");
		}
		//如果该员工是部门经理,把部门表中的经理编号字段设置为空
		try {
			departmentService.updateManagerIdToNull(employee.getEmpId());
			return employeeDao.updateEmployee(employee);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
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

	
	
	public int getEmpPhone(String empPhone) {
		return employeeDao.getEmpPhone(empPhone);
	}
}
