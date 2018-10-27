package com.puzhibing.StockAnalysis.dao.mapper;

import org.apache.ibatis.annotations.InsertProvider;

import com.puzhibing.StockAnalysis.dao.sql.CashFlowSql;
import com.puzhibing.StockAnalysis.pojo.CashFlow;

public interface CashFlowMapper {

	
	/**
	 * 添加数据
	 * @param cashFlow
	 */
	@InsertProvider(type = CashFlowSql.class , method = "insertCashFlow")
	void insertCashFlow(CashFlow cashFlow);
}
