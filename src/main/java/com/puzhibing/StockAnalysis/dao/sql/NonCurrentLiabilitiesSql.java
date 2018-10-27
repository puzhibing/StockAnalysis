package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.NonCurrentLiabilities;

public class NonCurrentLiabilitiesSql {

	
	/**
	 * 添加数据
	 * @param nonCurrentLiabilities
	 * @return
	 */
	public String insertNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities) {
		return new SQL() {{
			INSERT_INTO("t_nonCurrentLiabilities");
			INTO_COLUMNS("id , companyStockId , dataTime , longTermLoan , bondsPayable , longTermPayables");
			INTO_COLUMNS("specialPayable , estimatedLiabilities , deferredIncomeTaxLiabilities , otherNonCurrentLiabilities");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{longTermLoan} , #{bondsPayable} , #{longTermPayables}");
			INTO_VALUES("#{specialPayable} , #{estimatedLiabilities} , #{deferredIncomeTaxLiabilities} , #{otherNonCurrentLiabilities}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentLiabilities
	 * @return
	 */
	public String updateNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities) {
		return new SQL() {{
			UPDATE("t_nonCurrentLiabilities");
			SET("dataTime = #{dataTime} , longTermLoan = #{longTermLoan} , bondsPayable = #{bondsPayable} , longTermPayables = #{longTermPayables}");
			SET("specialPayable = #{specialPayable} , estimatedLiabilities = #{estimatedLiabilities} , deferredIncomeTaxLiabilities = #{deferredIncomeTaxLiabilities} , otherNonCurrentLiabilities = #{otherNonCurrentLiabilities}");
			SET("updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentLiabilities
	 * @return
	 */
	public String deleteNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities) {
		return new SQL() {{
			UPDATE("t_nonCurrentLiabilities");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectNonCurrentLiabilitiesByCompanyStockId(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , longTermLoan , bondsPayable , longTermPayables");
			SELECT("specialPayable , estimatedLiabilities , deferredIncomeTaxLiabilities , otherNonCurrentLiabilities");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_nonCurrentLiabilities");
			WHERE("del = '0' and companyStockId = #{companyStockId}");
		}}.toString();
	}
	
}
