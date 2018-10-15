package com.puzhibing.StockAnalysis.pojo;

import java.util.Date;


/**
 * 企业实体类
 * @author Administrator
 *
 */
public class Company extends TotalBean {

	private String chName;//中文名称
	
	private String chShortName;//中文简称
	
	private String enName;//英文名称
	
	private String enShortName;//英文简称
	
	private Date registerTime;//注册日期
	
	private String url;//网址
	
	private Object companyStocks;//公司股票

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChShortName() {
		return chShortName;
	}

	public void setChShortName(String chShortName) {
		this.chShortName = chShortName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnShortName() {
		return enShortName;
	}

	public void setEnShortName(String enShortName) {
		this.enShortName = enShortName;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getCompanyStocks() {
		return companyStocks;
	}

	public void setCompanyStocks(Object companyStocks) {
		this.companyStocks = companyStocks;
	}

	@Override
	public String toString() {
		return "Company [chName=" + chName + ", chShortName=" + chShortName + ", enName=" + enName + ", enShortName="
				+ enShortName + ", registerTime=" + registerTime + ", url=" + url + ", companyStocks=" + companyStocks
				+ "]";
	}

	
	
	
}
