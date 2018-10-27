package com.puzhibing.StockAnalysis.pojo;

/**
 * 股票类型实体类
 * @author Administrator
 *
 */
public class StockType extends TotalBean {

	private String name;//名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StockType [name=" + name + "]";
	}
	
	
}
