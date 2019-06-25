package com.song.play.cloud.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页封装类 用于做分页查询的基础类，封装了一些分页的相关属性
 * 
 * @author kj
 * @version v1.0
 * @param <T>
 */
public class PageResults<T> {
 
	// 下一页
	private int pageNo;
 
	// 当前页
	private int currentPage;
 
	// 每页个个数
	private int pageSize;
 
	// 总条数
	private int totalCount;
 
	// 总页数
	private int pageCount;
	
	private List<Integer> pageCodes;
 
	// 记录
	private List<T> results;
 
	public int getPageCount() {
		return pageCount;
	}
 
	
	
	public List<Integer> getPageCodes() {
		return pageCodes;
	}



	public void setPageCodes(List<Integer> pageCodes) {
		this.pageCodes = pageCodes;
	}



	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
 
	public int getPageNo() {
		if (pageNo <= 0) {
			return 1;
		} else {
			return pageNo > pageCount ? pageCount : pageNo;
		}
	}
 
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
 
	public List<T> getResults() {
		return results;
	}
 
	public void setResults(List<T> results) {
		this.results = results;
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
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public void resetPageNo() {
		pageNo = currentPage + 1;
		pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		this.pageCodes=new ArrayList<Integer>();
		for(int i=1;i<=pageCount;i++)
		{
			this.pageCodes.add(i);
		}
	}

}
