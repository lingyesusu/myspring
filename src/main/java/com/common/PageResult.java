package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageResult<E> {

	private int currPage; // 当前页
	private int totalPage; // 总页数
	private int prevPage; // 上一页
	private int nextPage; // 下一页
	private int pageSize; // 每页记录数
	private long totalSize; // 总记录数
	private List<E> data; // 数据集合

	public PageResult() {
	}

	// 构造初始化(并且计算总页数和设置分页参数)
	public PageResult(int currPage, long totalSize, int pageSize, List<E> data) {
		this.data = (data == null ? new ArrayList<E>() : data);
		this.currPage = currPage;
		this.totalSize = totalSize;
		if (totalSize != 0) {
			if (totalSize % pageSize == 0) {
				this.totalPage = (int) totalSize / pageSize;
			} else {
				this.totalPage = (int) totalSize / pageSize + 1;
			}
			this.pageSize = pageSize;
		} else {
			this.totalPage = 0;
			this.pageSize = 10;
		}
		this.prevPage = currPage - 1 <= 0 ? 1 : currPage - 1;
		this.nextPage = currPage + 1 >= totalPage ? totalPage : currPage + 1;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrevPage() {
		prevPage = currPage - 1 <= 0 ? 1 : currPage - 1;
		return prevPage;
	}

	public int getNextPage() {
		nextPage = currPage + 1 >= totalPage ? totalPage : currPage + 1;
		return nextPage;
	}

	public int getPageSize() {
		if (pageSize < 1) {
			pageSize = 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageResult [currentPage=" + currPage + ", totalPage=" + totalPage + ", totalSize=" + totalSize
				+ ", pageSize=" + pageSize + "]";
	}
	
	public Map<String,Object> toMap(){
		Map<String,Object> res = new HashMap<>();
		res.put("data", getData());
		res.put("currPage", getCurrPage());
		res.put("pageSize", getPageSize());
		res.put("totalPage", getTotalPage());
		res.put("totalSize", getTotalSize());
		return res;
	}
	
}
