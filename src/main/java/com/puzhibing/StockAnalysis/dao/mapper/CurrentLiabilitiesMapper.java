package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.CurrentLiabilitiesSql;
import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;

public interface CurrentLiabilitiesMapper {

	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 */
	@InsertProvider(type = CurrentLiabilitiesSql.class , method = "insertCurrentLiabilities")
	void insertCurrentLiabilities(CurrentLiabilities currentLiabilities);
	
	
	
	
	/**
	 * 修改数据
	 * @param currentLiabilities
	 */
	@UpdateProvider(type = CurrentLiabilitiesSql.class , method = "updateCurrentLiabilities")
	void updateCurrentLiabilities(CurrentLiabilities currentLiabilities);
	
	
	
	/**
	 * 删除数据
	 * @param currentLiabilities
	 */
	@UpdateProvider(type = CurrentLiabilitiesSql.class , method = "deleteCurrentLiabilities")
	void deleteCurrentLiabilities(CurrentLiabilities currentLiabilities);
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = CurrentLiabilitiesSql.class , method = "selectCurrentLiabilities")
	List<CurrentLiabilities> selectCurrentLiabilities(String companyStockId);
}
