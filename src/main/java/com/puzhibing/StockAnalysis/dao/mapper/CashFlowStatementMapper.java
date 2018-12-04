package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import com.puzhibing.StockAnalysis.dao.sql.CashFlowStatementSql;
import com.puzhibing.StockAnalysis.pojo.CashFlowStatement;

public interface CashFlowStatementMapper {
	
	
	/**
	 * 添加数据
	 * @param cashFlow
	 */
	@InsertProvider(type = CashFlowStatementSql.class , method = "insertCashFlowStatement")
	void insertCashFlowStatement(CashFlowStatement cashFlowStatement);
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = CashFlowStatementSql.class , method = "selectCashFlowStatement")
	List<CashFlowStatement> selectCashFlowStatementByCompanyStockId(String companyStockId);
}
