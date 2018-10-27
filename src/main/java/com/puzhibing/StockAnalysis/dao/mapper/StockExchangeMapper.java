package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.StockExchangeSql;
import com.puzhibing.StockAnalysis.pojo.StockExchange;

public interface StockExchangeMapper {

	
	
	/**
	 * 添加数据
	 * @param stockExchange
	 */
	@InsertProvider(type = StockExchangeSql.class , method = "insertStockExchange")
	void insertStockExchange(StockExchange stockExchange);
	
	
	
	/**
	 * 修改数据
	 * @param stockExchange
	 */
	@UpdateProvider(type = StockExchangeSql.class , method = "updateStockExchange")
	void updateStockExchange(StockExchange stockExchange);
	
	
	
	/**
	 * 删除数据
	 * @param stockExchange
	 */
	@UpdateProvider(type = StockExchangeSql.class , method = "deleteStockExchange")
	void deleteStockExchange(StockExchange stockExchange);
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = StockExchangeSql.class , method = "selectStockExchangeById")
	StockExchange selectStockExchangeById(String id);
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@SelectProvider(type = StockExchangeSql.class , method = "selectAllStockExchange")
	List<StockExchange> selectAllStockExchange();
}
