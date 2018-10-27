package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.NonCurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface NonCurrentAssetsService {
	
	
	/**
	 * 添加数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 */
	ResultBean<Object> insertNonCurrentAssets(NonCurrentAssets nonCurrentAssets , String currencyUnit , String token) throws Exception;
	
	
	/**
	 * 修改数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateNonCurrentAssets(NonCurrentAssets nonCurrentAssets , String token) throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteNonCurrentAssets(NonCurrentAssets nonCurrentAssets , String token) throws Exception;
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectNonCurrentAssetsByCompanyStockId(String companyStockId) throws Exception;

}
