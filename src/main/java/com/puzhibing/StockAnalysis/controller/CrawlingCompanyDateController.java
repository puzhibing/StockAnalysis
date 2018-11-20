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
	
	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 导入数据
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/crawlingShanghai")
	public ResultBean<Object> crawlingShanghai(String type , String stockTypeId,String stockExchangeName, String stockExchangeId , String token){
		return resultBean = crawlingCompanyDateImpl.crawlingCompany(type, stockTypeId, stockExchangeName, stockExchangeId, token);
	}
}
