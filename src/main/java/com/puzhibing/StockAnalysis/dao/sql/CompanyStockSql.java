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
			INSERT_INTO("t_companystock");
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
			UPDATE("t_companystock");
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
			FROM("t_companystock");
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
			UPDATE("t_companystock");
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
			FROM("t_companystock");
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
			FROM("t_companystock");
			WHERE("stockCode like #{stockCode} and del = '0'");
		}}.toString();
	}
	
	
	
	/**
	 * 根据企业id，证券类型id
	 * @param companyId
	 * @param stockTypeId
	 * @return
	 */
	public String selectCompanyStockByCompanyIdAndStockTypeId(String companyId , String stockTypeId){
		return new SQL() {{
			SELECT("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_companystock");
			WHERE("companyId = #{param1} and stockTypeId = #{param2}");
		}}.toString();
	}


	/**
	 * 根据行业id和证券类型id查询数据
	 * @param IndustryId
	 * @param stockTypeId
	 * @return
	 */
	public String selectCompanyStockByIndustryAndStockTypeId(String IndustryId , String stockTypeId){
		return new SQL() {{
			SELECT("id , companyId , stockCode , stockTypeId , listingTime , stockExchangeId , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_companystock  AS a");
			WHERE("a.del = '0' AND stockTypeId = #{param2} AND a.companyId IN (" +
					"SELECT id FROM t_company AS b WHERE b.industry = #{param1} AND b.del = '0'" +
					")");
		}}.toString();
	}
}
