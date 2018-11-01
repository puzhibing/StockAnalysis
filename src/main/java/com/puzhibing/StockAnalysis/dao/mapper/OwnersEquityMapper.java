package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import com.puzhibing.StockAnalysis.dao.sql.OwnersEquitySql;
import com.puzhibing.StockAnalysis.pojo.OwnersEquity;

public interface OwnersEquityMapper {

	/**
	 * 添加数据
	 * @param ownersEquity
	 */
	@InsertProvider(type = OwnersEquitySql.class , method = "insertOwnersEquity")
	void insertOwnersEquity(OwnersEquity ownersEquity);
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = OwnersEquitySql.class , method = "selectOwnersEquityByCompanyStockId")
	List<OwnersEquity> selectOwnersEquityByCompanyStockId(String companyStockId);
}
