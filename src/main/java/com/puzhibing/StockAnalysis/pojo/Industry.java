package com.puzhibing.StockAnalysis.pojo;

public class Industry extends TotalBean {

	private String code;//唯一编号
	
	private String name;//名称

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Industry [code=" + code + ", name=" + name + "]";
	}
	
	
}
