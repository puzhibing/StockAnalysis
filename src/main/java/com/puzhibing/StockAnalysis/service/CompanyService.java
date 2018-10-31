package com.puzhibing.StockAnalysis.service;


import com.puzhibing.StockAnalysis.pojo.Company;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CompanyService {

	
	
	/**
	 * 添加数据
	 * @param company
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> insertCompany(Company company , String token) throws Exception;
	
	
	/**
	 * 修改数据
	 * @param company
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateCompany(Company company, String token) throws Exception;
	
	
	
	/**
	 * 删除数据
	 * @param company
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteCompany(Company company , String token) throws Exception;
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectAllCompany() throws Exception;
	
	
	
	/**
	 * 查询单个企业详情数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCompanyInfoById(String id) throws Exception;
	
	
	
	/**
	 * 根据编号或名称模糊查询企业数据
	 * @param value	编号或名称
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> fuzzyQueryCompany(String value) throws Exception;
	
	
}
