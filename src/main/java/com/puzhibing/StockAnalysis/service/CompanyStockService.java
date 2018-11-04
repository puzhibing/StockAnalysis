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
	
	
	
	/**
	 * 根据编号模糊查询数据
	 * @param stockCode
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCompanyStockLikeCode(String stockCode) throws Exception;
	
	
	
	/**
	 * 根据企业证券id查询所有该证券的所有证券数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectAllSecuritiesDataById(String id) throws Exception;


	/**
	 * 根据证券编号查询企业数据和证券类型数据
	 * @param stockCode
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectAllDataLikeCode(String stockCode) throws Exception;
}
