package com.puzhibing.StockAnalysis.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.CompanyStockSql;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;

public interface CompanyStockMapper {

	
	
	/**
	 * 添加数据
	 * @param companyStock
	 */
	@InsertProvider(type = CompanyStockSql.class , method = "insertCompanyStock")
	void insertCompanyStock(CompanyStock companyStock);
	
	
	
	/**
	 * 删除数据
	 * @param companyStock
	 */
	@UpdateProvider(type = CompanyStockSql.class , method = "deleteCompanyStock")
	void deleteCompanyStock(CompanyStock companyStock);
	
	
	
	/**
	 * 根据企业id查询对应的所有数据
	 * @param companyId
	 * @return
	 */
	@SelectProvider(type = CompanyStockSql.class , method = "selectCompanyStockByCompanyId")
	@Results({
		@Result(property = "id" , column = "id" , id = true),
		@Result(property = "companyId" , column = "companyId"),
		@Result(property = "stockCode" , column = "stockCode"),
		@Result(property = "stockTypeId" , column = "stockTypeId" , one = @One(
				select = "com.puzhibing.StockAnalysis.dao.mapper.StockTypeMapper.selectStockTypeById"
				)),
		@Result(property = "listingTime" , column = "listingTime"),
		@Result(property = "stockExchangeId" , column = "stockExchangeId" , one = @One(
				select = "com.puzhibing.StockAnalysis.dao.mapper.StockExchangeMapper.selectStockExchangeById"
				)),
		@Result(property = "del" , column = "del"),
		@Result(property = "insertUserId" , column = "insertUserId"),
		@Result(property = "insertTime" , column = "insertTime"),
		@Result(property = "updateUserId" , column = "updateUserId"),
		@Result(property = "updateTime" , column = "updateTime")
	})
	List<CompanyStock> selectCompanyStockByCompanyId(String companyId);
	
	
	
	
	/**
	 * 修改数据
	 * @param companyStock
	 */
	@UpdateProvider(type = CompanyStockSql.class , method = "updateCompanyStock")
	void updateCompanyStock(CompanyStock companyStock);
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@SelectProvider(type = CompanyStockSql.class , method = "selectCompanyStockById")
	CompanyStock selectCompanyStockById(String id);
	
	
	
	/**
	 * 根据编号模糊查询数据
	 * @param stockCode
	 * @return
	 */
	@SelectProvider(type = CompanyStockSql.class , method = "selectCompanyStockLikeCode")
	List<CompanyStock> selectCompanyStockLikeCode(String stockCode);


	/**
	 * 根据证券编号模糊查询所有数据（包含证券类型和上市地址）
	 * @param stockCode
	 * @return
	 */
	@SelectProvider(type = CompanyStockSql.class , method = "selectCompanyStockLikeCode")
	@Results({
			@Result(property = "id" , column = "id" , id = true),
			@Result(property = "companyId" , column = "companyId"),
			@Result(property = "stockCode" , column = "stockCode"),
			@Result(property = "stockTypeId" , column = "stockTypeId" , one = @One(
					select = "com.puzhibing.StockAnalysis.dao.mapper.StockTypeMapper.selectStockTypeById"
			)),
			@Result(property = "listingTime" , column = "listingTime"),
			@Result(property = "stockExchangeId" , column = "stockExchangeId" , one = @One(
					select = "com.puzhibing.StockAnalysis.dao.mapper.StockExchangeMapper.selectStockExchangeById"
			)),
			@Result(property = "del" , column = "del"),
			@Result(property = "insertUserId" , column = "insertUserId"),
			@Result(property = "insertTime" , column = "insertTime"),
			@Result(property = "updateUserId" , column = "updateUserId"),
			@Result(property = "updateTime" , column = "updateTime")
	})
	List<CompanyStock> selectAllDataLikeCode(String stockCode);
}
