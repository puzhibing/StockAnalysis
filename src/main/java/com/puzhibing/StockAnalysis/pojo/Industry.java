package com.puzhibing.StockAnalysis.pojo;

public class Industry extends TotalBean {

	private String parentId;//父类id

	private String code;//唯一编号
	
	private String name;//名称

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

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
		return "Industry{" +
				"parentId='" + parentId + '\'' +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
