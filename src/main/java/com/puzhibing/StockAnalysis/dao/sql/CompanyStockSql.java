package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CompanyStock;

public class CompanyStockSql {

	
	
	/**
	 * 添加数据
	 * @param companyStock
	 * @return
	 */
	public String insertCompanyStock(CompanyStock companyStock) {
		return new SQL() {{
			INSERT_INTO("t_companyStock");
			INTO_COLUMNS("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			INTO_COLUMNS("insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{companyId} , #{stockCode} , #{stockTypeId} , #{listingTime} , #{stockExchangeId}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	
	/**
	 * 删除数据
	 * @param companyStock
	 * @return
	 */
	public String deleteCompanyStock(CompanyStock companyStock) {
		return new SQL() {{
			UPDATE("t_companyStock");
			SET("del = '-1' , updateTime = #{updateTime} , updateUserId = #{updateUserId}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 根据企业id查询对应的所有数据
	 * @param companyId
	 * @return
	 */
	public String selectCompanyStockByCompanyId(String companyId) {
		return new SQL() {{
			SELECT("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_companyStock");
			WHERE("del = '0' and companyId = #{companyId}");
		}}.toString();
	}
	
	
	
	/**
	 * 修改数据
	 * @param companyStock
	 * @return
	 */
	public String updateCompanyStock(CompanyStock companyStock){
		return new SQL() {{
			UPDATE("t_companyStock");
			SET("stockCode = #{stockCode} , stockTypeId = #{stockTypeId} , listingTime = #{listingTime}");
			SET("stockExchangeId = #{stockExchangeId} , updateTime = #{updateTime} , updateUserId = #{updateUserId}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public String selectCompanyStockById(String id) {
		return new SQL() {{
			SELECT("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_companyStock");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 根据编号模糊查询数据
	 * @param stockCode
	 * @return
	 */
	public String selectCompanyStockLikeCode(String stockCode) {
		return new SQL() {{
			SELECT("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_companyStock");
			WHERE("stockCode like #{stockCode} and del = '0'");
		}}.toString();
	}
}
