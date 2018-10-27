package com.puzhibing.StockAnalysis.dao.mapper;

import org.apache.ibatis.annotations.InsertProvider;

import com.puzhibing.StockAnalysis.dao.sql.ProfitSql;
import com.puzhibing.StockAnalysis.pojo.Profit;

/**
 * 利润
 * @author asus
 *
 */
public interface ProfitMapper {

	
	
	/**
	 * 添加数据
	 * @param profit
	 */
	@InsertProvider(type = ProfitSql.class , method = "insertProfit")
	void insertProfit(Profit profit);
}
