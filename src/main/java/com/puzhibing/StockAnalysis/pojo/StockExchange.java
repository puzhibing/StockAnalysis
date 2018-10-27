package com.puzhibing.StockAnalysis.pojo;


/**
 * 证券交易所实体类
 * @author Administrator
 *
 */
public class StockExchange extends TotalBean {

	private String name;//名称
	
	private String address;//地址

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StockExchange [name=" + name + ", address=" + address + "]";
	}
	
	
}
