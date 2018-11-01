package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.OwnersEquityChange;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface OwnersEquityChangeService {

	
	
	/**
	 * 添加数据
	 * @param ownersEquityChange
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertOwnersEquityChange(OwnersEquityChange ownersEquityChange , String currencyUnit, String token) throws Exception;
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectOwnersEquityChangeByCompanyStockId(String companyStockId) throws Exception;
}
