package org.lzdn.platform.dto.base;

public class Pager {

	// 用户输入的分页条件
	private int currentPage = 1; // 当前页
	private int pageSize = 4; // 每页最大行数

	// 用于实现分页SQL的条件，是根据用户输入条件计算而来的
	private int begin;
	private int end;

	// 自动计算出的总行数
	private int rows;
	// 根据总行数计算总页数，然后将总页数输出给页面
	private int totalPage;
	//是否分页
	private boolean splitPage;

	public boolean isSplitPage() {
		return splitPage;
	}

	public void setSplitPage(boolean splitPage) {
		this.splitPage = splitPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalPage() {
		// 根据总行数，计算总页数
		if (rows % pageSize == 0) {
			totalPage = rows / pageSize;
		} else {
			totalPage = rows / pageSize + 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBegin() {
		// 在mapper.xml使用begin属性时，对其进行计算
		begin = (currentPage - 1) * pageSize;
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		// 在mapper.xml使用end属性时，对其进行计算
		end = currentPage * pageSize + 1;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
