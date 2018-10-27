package com.puzhibing.StockAnalysis.pojo;

import java.util.Date;


/**
 * 企业股票实体类
 * @author Administrator
 *
 */
public class CompanyStock extends TotalBean {

	private String companyId;//企业id
	
	private String stockCode;//证券编号
	
	private Object stockTypeId;//股票类型id
	
	private Date listingTime;//上市时间
	
	private Object stockExchangeId;//上市交易所id

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Object getStockTypeId() {
		return stockTypeId;
	}

	public void setStockTypeId(Object stockTypeId) {
		this.stockTypeId = stockTypeId;
	}

	public Date getListingTime() {
		return listingTime;
	}

	public void setListingTime(Date listingTime) {
		this.listingTime = listingTime;
	}

	public Object getStockExchangeId() {
		return stockExchangeId;
	}

	public void setStockExchangeId(Object stockExchangeId) {
		this.stockExchangeId = stockExchangeId;
	}

	@Override
	public String toString() {
		return "CompanyStock [companyId=" + companyId + ", stockCode=" + stockCode + ", stockTypeId=" + stockTypeId
				+ ", listingTime=" + listingTime + ", stockExchangeId=" + stockExchangeId + "]";
	}

	
	
	
}
