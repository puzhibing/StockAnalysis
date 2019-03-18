package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.CurrentAssetsSql;
import com.puzhibing.StockAnalysis.pojo.CurrentAssets;

public interface CurrentAssetsMapper {

	
	/**
	 * 添加数据
	 * @param currentAssets
	 */
	@InsertProvider(type = CurrentAssetsSql.class , method = "insertCurrentAssets")
	void insertCurrentAssets(CurrentAssets currentAssets);
	
	
	
	/**
	 * 修改数据
	 * @param currentAssets
	 */
	@UpdateProvider(type = CurrentAssetsSql.class , method = "updateCurrentAssets")
	void updateCurrentAssets(CurrentAssets currentAssets);
	
	
	
	/**
	 * 删除数据
	 * @param currentAssets
	 */
	@UpdateProvider(type = CurrentAssetsSql.class , method = "deleteCurrentAssets")
	void deleteCurrentAssets(CurrentAssets currentAssets);
	
	
	
	/**
	 * 根据企业股票id查询数据，排序
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = CurrentAssetsSql.class , method = "selectCurrentAssetsBycompanyStockId")
	List<CurrentAssets> selectCurrentAssetsBycompanyStockId(String companyStockId , Date startTime , Date endTime);
}
