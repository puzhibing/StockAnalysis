package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

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
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@SelectProvider(type = OwnersEquityChangeSql.class , method = "selectOwnersEquityChangeByCompanyStockId")
	List<OwnersEquityChange> selectOwnersEquityChangeByCompanyStockId(String companyStockId);
}
