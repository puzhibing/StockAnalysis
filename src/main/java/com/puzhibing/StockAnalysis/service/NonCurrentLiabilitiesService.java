package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.NonCurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface NonCurrentLiabilitiesService {

	
	
	/**
	 * 添加数据
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> insertNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities , String currencyUnit , String token) throws Exception;
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> updateNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities , String token) throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> deleteNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities , String token) throws Exception;
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	ResultBean<Object> selectNonCurrentLiabilitiesByCompanyStockId(String companyStockId);
}
