package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.NonCurrentAssetsSql;
import com.puzhibing.StockAnalysis.pojo.NonCurrentAssets;

public interface NonCurrentAssetsMapper {

	
	
	/**
	 * 添加数据
	 * @param nonCurrentAssets
	 */
	@InsertProvider(type = NonCurrentAssetsSql.class , method = "insertNonCurrentAssets")
	void insertNonCurrentAssets(NonCurrentAssets nonCurrentAssets);
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentAssets
	 */
	@UpdateProvider(type = NonCurrentAssetsSql.class , method = "updateNonCurrentAssets")
	void updateNonCurrentAssets(NonCurrentAssets nonCurrentAssets);
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentAssets
	 */
	@UpdateProvider(type = NonCurrentAssetsSql.class , method = "deleteNonCurrentAssets")
	void deleteNonCurrentAssets(NonCurrentAssets nonCurrentAssets);
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = NonCurrentAssetsSql.class , method = "selectNonCurrentAssetsByCompanyStockId")
	List<NonCurrentAssets> selectNonCurrentAssetsByCompanyStockId(String companyStockId , Date startTime , Date endTime);
}
