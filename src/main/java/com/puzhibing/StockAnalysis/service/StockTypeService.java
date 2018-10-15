package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockType;

public interface StockTypeService {
	
	
	
	/**
	 * 添加数据
	 * @param stockType
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> insertStockType(StockType stockType , String token) throws Exception;
	
	
	
	
	
	/**
	 * 修改数据
	 * @param stockType
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateStockType(StockType stockType , String token) throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * @param stockType
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteStockType(StockType stockType , String token) throws Exception;
	
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectAllStockType() throws Exception;
	
	

}
