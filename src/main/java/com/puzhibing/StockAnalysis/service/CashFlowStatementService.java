package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.CashFlowStatement;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CashFlowStatementService {

	/**
	 * 添加数据
	 * @param cashFlowStatement
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertCashFlowStatement(CashFlowStatement cashFlowStatement 
			, String currencyUnit , String token) throws Exception;
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCashFlowStatementByCompanyStockId(String companyStockId) throws Exception;
}
