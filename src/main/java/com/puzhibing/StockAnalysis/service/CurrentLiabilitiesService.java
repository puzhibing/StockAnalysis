package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CurrentLiabilitiesService {

	
	
	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertCurrentLiabilities(CurrentLiabilities currentLiabilities , String currencyUnit , String token) throws Exception;
	
	
	
	/**
	 * 修改数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateCurrentLiabilities(CurrentLiabilities currentLiabilities , String token) throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteCurrentLiabilities(CurrentLiabilities currentLiabilities , String token) throws Exception;
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCurrentLiabilities(String companyStockId) throws Exception;
}
