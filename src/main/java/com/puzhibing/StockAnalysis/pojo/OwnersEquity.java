package com.puzhibing.StockAnalysis.pojo;


/**
 * 所有者权益
 * @author Administrator
 *
 */
public class OwnersEquity extends StockDataBean {

	private String pic;//股本

	private String oei;//其他权益工具
	
	private String capitalReserve;//资本公积

	private String oci;//其他综合收益
	
	private String lts;//减：库存股

	private String specialReserves;//专项储备
	
	private String surplusReserve;//盈余公积

	private String grp;//一般风险准备
	
	private String undistributedProfit;//未分配利润

	private String toeattpc;//归属于母公司所有者权益合计

	private String mse;//少数股东权益

	private String toe;//所有者权益合计

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getOei() {
		return oei;
	}

	public void setOei(String oei) {
		this.oei = oei;
	}

	public String getCapitalReserve() {
		return capitalReserve;
	}

	public void setCapitalReserve(String capitalReserve) {
		this.capitalReserve = capitalReserve;
	}

	public String getOci() {
		return oci;
	}

	public void setOci(String oci) {
		this.oci = oci;
	}

	public String getLts() {
		return lts;
	}

	public void setLts(String lts) {
		this.lts = lts;
	}

	public String getSpecialReserves() {
		return specialReserves;
	}

	public void setSpecialReserves(String specialReserves) {
		this.specialReserves = specialReserves;
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

	public String getToeattpc() {
		return toeattpc;
	}

	public void setToeattpc(String toeattpc) {
		this.toeattpc = toeattpc;
	}

	public String getMse() {
		return mse;
	}

	public void setMse(String mse) {
		this.mse = mse;
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
				", oei='" + oei + '\'' +
				", capitalReserve='" + capitalReserve + '\'' +
				", oci='" + oci + '\'' +
				", lts='" + lts + '\'' +
				", specialReserves='" + specialReserves + '\'' +
				", surplusReserve='" + surplusReserve + '\'' +
				", grp='" + grp + '\'' +
				", undistributedProfit='" + undistributedProfit + '\'' +
				", toeattpc='" + toeattpc + '\'' +
				", mse='" + mse + '\'' +
				", toe='" + toe + '\'' +
				'}';
	}
}
