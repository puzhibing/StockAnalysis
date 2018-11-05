package com.puzhibing.StockAnalysis.pojo.reptileBean;

import java.util.List;

public class SHJSONData {

	private String areaName;
	
	private String csrcCode;
	
	private String downloadFileName;
	
	private String execlStream;
	
	private String jsonCallBack;
	
	private SHPageHelp pageHelp;
	
	private List<SHResult> result;
	
	private String stockCode;
	
	private String stockType;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCsrcCode() {
		return csrcCode;
	}

	public void setCsrcCode(String csrcCode) {
		this.csrcCode = csrcCode;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getExeclStream() {
		return execlStream;
	}

	public void setExeclStream(String execlStream) {
		this.execlStream = execlStream;
	}

	public String getJsonCallBack() {
		return jsonCallBack;
	}

	public void setJsonCallBack(String jsonCallBack) {
		this.jsonCallBack = jsonCallBack;
	}

	public SHPageHelp getPageHelp() {
		return pageHelp;
	}

	public void setPageHelp(SHPageHelp pageHelp) {
		this.pageHelp = pageHelp;
	}

	public List<SHResult> getResult() {
		return result;
	}

	public void setResult(List<SHResult> result) {
		this.result = result;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Override
	public String toString() {
		return "SHJSONData [areaName=" + areaName + ", csrcCode=" + csrcCode + ", downloadFileName=" + downloadFileName
				+ ", execlStream=" + execlStream + ", jsonCallBack=" + jsonCallBack + ", pageHelp=" + pageHelp
				+ ", result=" + result + ", stockCode=" + stockCode + ", stockType=" + stockType + "]";
	}
	
	
}
