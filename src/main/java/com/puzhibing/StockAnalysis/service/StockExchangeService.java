package com.puzhibing.StockAnalysis.service;


import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockExchange;

public interface StockExchangeService {


	
	/**
	 * 添加数据
	 * @param stockExchange
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> insertStockExchange(StockExchange stockExchange , String token) throws Exception;
	
	
	
	/**
	 * 修改数据
	 * @param stockExchange
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateStockExchange(StockExchange stockExchange , String token) throws Exception;
	
	
	
	/**
	 * 删除数据
	 * @param id
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteStockExchange(String id , String token) throws Exception;
	
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectAllStockExchange() throws Exception;
}
