package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.NonCurrentLiabilitiesSql;
import com.puzhibing.StockAnalysis.pojo.NonCurrentLiabilities;

public interface NonCurrentLiabilitiesMapper {

	
	
	/**
	 * 添加数据
	 * @param nonCurrentLiabilities
	 */
	@InsertProvider(type = NonCurrentLiabilitiesSql.class , method = "insertNonCurrentLiabilities")
	void insertNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities);
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentLiabilities
	 */
	@UpdateProvider(type = NonCurrentLiabilitiesSql.class , method = "updateNonCurrentLiabilities")
	void updateNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities);
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentLiabilities
	 */
	@UpdateProvider(type = NonCurrentLiabilitiesSql.class , method = "deleteNonCurrentLiabilities")
	void deleteNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities);
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = NonCurrentLiabilitiesSql.class , method = "selectNonCurrentLiabilitiesByCompanyStockId")
	List<NonCurrentLiabilities> selectNonCurrentLiabilitiesByCompanyStockId(String companyStockId);
	
}
