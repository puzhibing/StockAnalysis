package com.puzhibing.StockAnalysis.pojo;


/**
 * 所有者权益
 * @author Administrator
 *
 */
public class OwnersEquity extends StockDataBean {

	private String paidInCapital;//实收资本
	
	private String capitalReserve;//资本公积
	
	private String lessTreasuryShares;//减：库存股
	
	private String surplusReserve;//盈余公积
	
	private String undistributedProfit;//未分配利润

	public String getPaidInCapital() {
		return paidInCapital;
	}

	public void setPaidInCapital(String paidInCapital) {
		this.paidInCapital = paidInCapital;
	}

	public String getCapitalReserve() {
		return capitalReserve;
	}

	public void setCapitalReserve(String capitalReserve) {
		this.capitalReserve = capitalReserve;
	}

	public String getLessTreasuryShares() {
		return lessTreasuryShares;
	}

	public void setLessTreasuryShares(String lessTreasuryShares) {
		this.lessTreasuryShares = lessTreasuryShares;
	}

	public String getSurplusReserve() {
		return surplusReserve;
	}

	public void setSurplusReserve(String surplusReserve) {
		this.surplusReserve = surplusReserve;
	}

	public String getUndistributedProfit() {
		return undistributedProfit;
	}

	public void setUndistributedProfit(String undistributedProfit) {
		this.undistributedProfit = undistributedProfit;
	}

	@Override
	public String toString() {
		return "OwnersEquity [paidInCapital=" + paidInCapital + ", capitalReserve=" + capitalReserve
				+ ", lessTreasuryShares=" + lessTreasuryShares + ", surplusReserve=" + surplusReserve
				+ ", undistributedProfit=" + undistributedProfit + "]";
	}
	
	
}
