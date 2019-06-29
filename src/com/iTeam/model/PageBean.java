package com.iTeam.model;

/**
 * 分页
 *
 */
public class PageBean {

	private int page;
	private int pageSize;
	private int start;
	
	public PageBean(int page, int pageSize) {
		super();
		if(page<1) {
			throw new IllegalArgumentException();
		}
		this.page = page;
		this.pageSize = pageSize;
		this.start=(page - 1) * pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return this.start;
	}

}
