package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.Profit;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

/**
 * 利润
 * @author asus
 *
 */
public interface ProfitService {

	
	/**
	 * 添加数据
	 * @param profit
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertProfit(Profit profit , String currencyUnit , String token)throws Exception;
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectProfitByCompanyStockId(String companyStockId) throws Exception;
}
