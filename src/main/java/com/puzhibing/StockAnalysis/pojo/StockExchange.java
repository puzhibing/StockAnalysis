package com.puzhibing.StockAnalysis.pojo;


/**
 * 证券交易所实体类
 * @author Administrator
 *
 */
public class StockExchange extends TotalBean {

	private String name;//名称
	
	private String url;//网址

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "StockExchange [name=" + name + ", url=" + url + "]";
	}

	
	
	
}
