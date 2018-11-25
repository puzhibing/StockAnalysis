package com.puzhibing.StockAnalysis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CrawlingCompanyDate;

@RestController
public class CrawlingCompanyDateController {

	@Autowired
	private CrawlingCompanyDate crawlingCompanyDateImpl;
	
	
	
	/**
	 * 爬取数据
	 * @param type
	 * @param stockTypeId
	 * @param stockExchangeName
	 * @param stockExchangeId
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/crawlingCompany")
	public ResultBean<Object> crawlingCompany(String type, String stockTypeId, String stockExchangeName,
			String stockExchangeId, String token){
		return crawlingCompanyDateImpl.crawlingCompany(type, stockTypeId, stockExchangeName, stockExchangeId, token);
	}
}
