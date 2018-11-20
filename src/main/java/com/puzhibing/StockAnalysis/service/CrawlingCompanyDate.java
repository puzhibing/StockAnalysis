package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CrawlingCompanyDate {

	
	/**
	 * 爬取企业数据
	 * @param type
	 * @param stockTypeId
	 * @param stockExchangeName
	 * @param stockExchangeId
	 * @param token
	 * @return
	 */
	ResultBean<Object> crawlingCompany(String type, String stockTypeId, String stockExchangeName, String stockExchangeId, String token);
	
	
}
