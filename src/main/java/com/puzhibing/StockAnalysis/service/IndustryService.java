package com.puzhibing.StockAnalysis.service;

import java.util.List;

import com.puzhibing.StockAnalysis.pojo.Industry;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface IndustryService {

	
	/**
	 * 添加数据
	 * @param industry
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertIndustry(Industry industry, String token) throws Exception;
	
	
	/**
	 * 修改数据
	 * @param industry
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateIndustry(Industry industry, String token) throws Exception;
	
	
	/**
	 * 删除数据
	 * @param industry
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteIndustry(Industry industry, String token) throws Exception;
	
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	ResultBean<List<Industry>> selectAllIndustry() throws Exception;
}
