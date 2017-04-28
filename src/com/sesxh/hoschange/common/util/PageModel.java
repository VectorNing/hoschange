package com.sesxh.hoschange.common.util;

public class PageModel {
	private Integer rows;// 一页显示的条目
	private Integer page;// 当前页数

	public Integer getStartSize() {
		return (page - 1) * rows;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPageNumber(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "PageModel [rows=" + rows + ", pageNumber=" + page + "]";
	}

}
