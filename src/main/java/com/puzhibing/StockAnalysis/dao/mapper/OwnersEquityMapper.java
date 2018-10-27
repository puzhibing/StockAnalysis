package com.puzhibing.StockAnalysis.dao.mapper;

import org.apache.ibatis.annotations.InsertProvider;

import com.puzhibing.StockAnalysis.dao.sql.OwnersEquitySql;
import com.puzhibing.StockAnalysis.pojo.OwnersEquity;

public interface OwnersEquityMapper {

	/**
	 * 添加数据
	 * @param ownersEquity
	 */
	@InsertProvider(type = OwnersEquitySql.class , method = "insertOwnersEquity")
	void insertOwnersEquity(OwnersEquity ownersEquity);
}
