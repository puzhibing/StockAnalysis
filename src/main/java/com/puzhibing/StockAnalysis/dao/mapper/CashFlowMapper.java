package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import com.puzhibing.StockAnalysis.dao.sql.CashFlowSql;
import com.puzhibing.StockAnalysis.pojo.CashFlow;

public interface CashFlowMapper {

	
	/**
	 * 添加数据
	 * @param cashFlow
	 */
	@InsertProvider(type = CashFlowSql.class , method = "insertCashFlow")
	void insertCashFlow(CashFlow cashFlow);
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = CashFlowSql.class , method = "selectCashFlowByCompanyStockId")
	List<CashFlow> selectCashFlowByCompanyStockId(String companyStockId, Date startTime, Date endTime);
}
