package com.iTeam.dao;

import java.util.List;
import java.util.Map;

import com.iTeam.model.Department;

/**
 * 部门信息管理Dao层
 * @author guyue_NO1
 *
 */
public interface DepartmentDao {

	/**
	 * 查询部门信息列表
	 * @param map
	 * @return
	 */
	public List<Department> getDepartmentList(Map<String, Object> map);

	/**
	 * 获取总记录数
	 * @return
	 */
	public long getTotalDepartment();

	/**
	 * 新增部门信息
	 * @param department
	 * @return
	 */
	public int addDepartment(Department department);

	/**
	 * 更新部门信息
	 * @param department
	 * @return
	 */
	public int updateDepartment(Department department);

	/**
	 * 删除一条部门信息
	 * @param deptId
	 * @return
	 */
	public int deleteDepartment(int deptId);

	/**
	 * 批量删除部门信息
	 * @param deptIds
	 * @return
	 */
	public int deleteDepartmentBatch(List<Integer> deptIds);

	/**
	 * 根据员工Id,如果该员工是某部门的经理，设置该部门经理Id为空
	 * @param empId
	 * @return
	 */
	public int updateManagerIdToNull(int empId);

	/**
	 * 根据员工Id,如果哲学员工是某些部门的经理，设置这些部门经理Id为空
	 * @param empIds
	 * @return
	 */
	public int updateManagerIdsToNull(List<Integer> empIds);

}
