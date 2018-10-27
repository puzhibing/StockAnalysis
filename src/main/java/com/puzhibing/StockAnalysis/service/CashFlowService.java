package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.CashFlow;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CashFlowService {

	/**
	 * 添加数据
	 * @param cashFlow
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertCashFlow(CashFlow cashFlow , String currencyUnit , String token) throws Exception;
}
