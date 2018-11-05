package com.puzhibing.StockAnalysis.pojo.reptileBean;

import java.util.List;

public class SHPageHelp {

	private String beginPage;
	
	private String cacheSize;
	
	private List<SHData> data;
	
	private String endDate;
	
	private String endPage;
	
	private String objectResult;
	
	private String pageCount;
	
	private String pageNo;
	
	private String pageSize;
	
	private String searchDate;
	
	private String sort;
	
	private String startDate;
	
	private String total;

	public String getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(String beginPage) {
		this.beginPage = beginPage;
	}

	public String getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(String cacheSize) {
		this.cacheSize = cacheSize;
	}

	public List<SHData> getData() {
		return data;
	}

	public void setData(List<SHData> data) {
		this.data = data;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndPage() {
		return endPage;
	}

	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}

	public String getObjectResult() {
		return objectResult;
	}

	public void setObjectResult(String objectResult) {
		this.objectResult = objectResult;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SHPageHelp [beginPage=" + beginPage + ", cacheSize=" + cacheSize + ", data=" + data + ", endDate="
				+ endDate + ", endPage=" + endPage + ", objectResult=" + objectResult + ", pageCount=" + pageCount
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", searchDate=" + searchDate + ", sort=" + sort
				+ ", startDate=" + startDate + ", total=" + total + "]";
	}
	
	
	
	
}
