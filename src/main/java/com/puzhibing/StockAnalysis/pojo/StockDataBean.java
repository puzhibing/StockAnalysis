package com.puzhibing.StockAnalysis.pojo;


/**
 * 定义数据抽象类
 * @author Administrator
 *
 */
public abstract class StockDataBean extends TotalBean {

	public String companyStockId;//企业股票id
	
	public String dataTime;//数据发生日期

	public String getCompanyStockId() {
		return companyStockId;
	}

	public void setCompanyStockId(String companyStockId) {
		this.companyStockId = companyStockId;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	@Override
	public String toString() {
		return "StockDataBean [companyStockId=" + companyStockId + ", dataTime=" + dataTime + "]";
	}
	
	
}
