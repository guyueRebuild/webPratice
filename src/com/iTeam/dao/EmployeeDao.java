package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Employee;

/**
 * 员工信息管理Dao层
 * @author guyue_NO1
 *
 */
public interface EmployeeDao {
	/**
	 * 获取员工信息列表
	 * @param map
	 * @return
	 */
	public List<Employee> getEmployeeList(Map<String, Object> map);

	/**
	 * 根据部门Id查询员工列表
	 * @param map
	 * @return
	 */
	public List<Employee> getEmployeeListByDeptId(Map<String, Object> map);

	/**
	 * 获取总记录数
	 * @return
	 */
	public long getTotalEmployee();

	/**
	 * 新增员工信息
	 * @param employee
	 * @return
	 */
	public int addEmployee(Employee employee);

	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	public int updateEmployee(Employee employee);

	/**
	 * 根据部门Id，设置该部门下员工的部门Id为默认初始值
	 * @param deptIds
	 * @return
	 */
	public int updateDeptIdToDefaultByDeptIds(List<Integer> deptIds);
	
	/**
	 * 删除一条员工记录
	 * @param empId
	 * @return
	 */
	public int deleteEmployee(int empId);

	/**
	 * 删除员工记录
	 * @param empIds
	 * @return
	 */
	public int deleteEmployeeBatch(List<Integer> empIds);
	
	
	/**
	 * 查询是否有对应的号码
	 * @param empPhone
	 * @return
	 */
	public int getEmpPhone(String empPhone);
}
