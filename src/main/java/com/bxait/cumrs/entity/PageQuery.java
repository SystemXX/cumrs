package com.bxait.cumrs.entity;

public class PageQuery  {

	private Integer page = 0; // 页数
	private Integer limit = 10;// 每页条数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
