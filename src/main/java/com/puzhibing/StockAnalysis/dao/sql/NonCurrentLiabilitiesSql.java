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
			INSERT_INTO("t_noncurrentliabilities");
			INTO_COLUMNS("id , companyStockId , dataTime , ltl , bondsPayable , ltp , specialPayable , estimatedLiabilities , deferredIncome , ditl , dncl , tncl");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{ltl} , #{bondsPayable} , #{ltp} , #{specialPayable} , #{estimatedLiabilities} , #{deferredIncome} , #{ditl} , #{dncl} , #{tncl}");
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
			UPDATE("t_noncurrentliabilities");
			SET("dataTime = #{dataTime} , ltl = #{ltl} , bondsPayable = #{bondsPayable} , ltp = #{ltp} , specialPayable = #{specialPayable}");
			SET("estimatedLiabilities = #{estimatedLiabilities} , deferredIncome = #{deferredIncome} , ditl = #{ditl} , dncl = #{dncl} , tncl = #{tncl}");
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
			UPDATE("t_noncurrentliabilities");
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
			SELECT("id , companyStockId , dataTime , ltl , bondsPayable , ltp , specialPayable , estimatedLiabilities , deferredIncome , ditl , dncl , tncl");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_noncurrentliabilities");
			WHERE("del = '0' and companyStockId = #{companyStockId}");
		}}.toString();
	}
	
}
