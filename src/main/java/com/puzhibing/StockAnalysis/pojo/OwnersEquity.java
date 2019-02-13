package com.puzhibing.StockAnalysis.pojo;


/**
 * 所有者权益
 * @author Administrator
 *
 */
public class OwnersEquity extends StockDataBean {

	private String pic;//实收资本
	
	private String capitalReserve;//资本公积
	
	private String lts;//减：库存股
	
	private String surplusReserve;//盈余公积

	private String grp;//一般风险准备
	
	private String undistributedProfit;//未分配利润

	private String toe;//所有者权益合计

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCapitalReserve() {
		return capitalReserve;
	}

	public void setCapitalReserve(String capitalReserve) {
		this.capitalReserve = capitalReserve;
	}

	public String getLts() {
		return lts;
	}

	public void setLts(String lts) {
		this.lts = lts;
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

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getToe() {
		return toe;
	}

	public void setToe(String toe) {
		this.toe = toe;
	}

	@Override
	public String toString() {
		return "OwnersEquity{" +
				"pic='" + pic + '\'' +
				", capitalReserve='" + capitalReserve + '\'' +
				", lts='" + lts + '\'' +
				", surplusReserve='" + surplusReserve + '\'' +
				", grp='" + grp + '\'' +
				", undistributedProfit='" + undistributedProfit + '\'' +
				", toe='" + toe + '\'' +
				'}';
	}
}
