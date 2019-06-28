package com.iTeam.model;

public class Department {
	private int deptId;
	private String deptName;
	private int deptManagerId;
	private String deptManagerName;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptManagerId() {
		return deptManagerId;
	}
	public void setDeptManagerId(int deptManagerId) {
		this.deptManagerId = deptManagerId;
	}
	public String getDeptManagerName() {
		return deptManagerName;
	}
	public void setDeptManagerName(String deptManagerName) {
		this.deptManagerName = deptManagerName;
	}
}
