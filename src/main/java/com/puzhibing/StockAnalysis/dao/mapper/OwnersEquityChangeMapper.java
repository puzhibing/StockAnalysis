package com.puzhibing.StockAnalysis.dao.mapper;

import org.apache.ibatis.annotations.InsertProvider;

import com.puzhibing.StockAnalysis.dao.sql.OwnersEquityChangeSql;
import com.puzhibing.StockAnalysis.pojo.OwnersEquityChange;


/**
 * 所有者权益
 * @author asus
 *
 */
public interface OwnersEquityChangeMapper {

	
	
	/**
	 * 添加数据
	 * @param ownersEquityChange
	 */
	@InsertProvider(type = OwnersEquityChangeSql.class , method = "insertOwnersEquityChange")
	void insertOwnersEquityChange(OwnersEquityChange ownersEquityChange);
}
