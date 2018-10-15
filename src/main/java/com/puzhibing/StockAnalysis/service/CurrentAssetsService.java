package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.CurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface CurrentAssetsService {
	
	
	/**
	 * 添加数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> insertCurrentAssets(CurrentAssets currentAssets , String token)	throws Exception;
	
	
	
	/**
	 * 修改数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> updateCurrentAssets(CurrentAssets currentAssets , String token)	throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> deleteCurrentAssets(CurrentAssets currentAssets , String token) throws Exception;
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	ResultBean<Object> selectCurrentAssetsBycompanyStockId(String companyStockId)	throws Exception;

}
