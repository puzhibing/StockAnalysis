package com.puzhibing.StockAnalysis.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CrawlingCompanyDate {

	
	/**
	 * 爬取上海数据
	 * @param type	类型
	 * @return
	 * @throws MalformedURLException 
	 * @throws IOException 
	 */
	ResultBean<Object> crawlingShanghai(String type , String stockTypeId , String stockExchangeId , String token) throws MalformedURLException, IOException;
}
