package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.OwnersEquity;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface OwnersEquityService {

	
	/**
	 * 添加数据
	 * @param ownersEquityService
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertOwnersEquity(OwnersEquity ownersEquity , String currencyUnit, String token) throws Exception;
}
