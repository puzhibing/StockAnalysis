package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.StockTypeSql;
import com.puzhibing.StockAnalysis.pojo.StockType;

public interface StockTypeMapper {

	
	
	/**
	 * 添加数据
	 * @param stockType
	 */
	@InsertProvider(type = StockTypeSql.class , method = "insertStockType")
	void insertStockType(StockType stockType);
	
	
	
	/**
	 * 修改数据
	 * @param stockType
	 */
	@UpdateProvider(type = StockTypeSql.class , method = "updateStockType")
	void updateStockType(StockType stockType);
	
	
	
	/**
	 * 删除数据
	 * @param stockType
	 */
	@DeleteProvider(type = StockTypeSql.class , method = "deleteStockType")
	void deleteStockType(StockType stockType);
	
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = StockTypeSql.class , method = "selectStockTypeById")
	StockType selectStockTypeById(String id);
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@SelectProvider(type = StockTypeSql.class , method = "selectAllStockType")
	List<StockType> selectAllStockType();
}
