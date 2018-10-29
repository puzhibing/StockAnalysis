package com.puzhibing.StockAnalysis.service;


import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CompanyStockService {

	
	
	/**
	 * 添加数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> insertCompanyStock(CompanyStock companyStock , String token) throws Exception;
	
	
	
	/**
	 * 删除数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteCompanyStock(String id , String token) throws Exception;
	
	
	
	
	/**
	 * 根据企业id查询对应的所有数据
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCompanyStockByCompanyId(String companyId) throws Exception;
	
	
	/**
	 * 修改数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateCompanyStock(CompanyStock companyStock , String token) throws Exception;
}
