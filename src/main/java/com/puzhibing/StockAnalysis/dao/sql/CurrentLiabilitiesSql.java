package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;

public class CurrentLiabilitiesSql {

	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 * @return
	 */
	public String insertCurrentLiabilities(CurrentLiabilities currentLiabilities) {
		return new SQL() {{
			INSERT_INTO("t_currentLiabilities");
			INTO_COLUMNS("id , companyStockId , dataTime , shortTermLoan , transactionalFinancialLiabilities , billsPayable");
			INTO_COLUMNS("accountsPayable , advancePayment , payrollPayable , taxesPayable , interestPayable , dividendPayable");
			INTO_COLUMNS("otherPayables , nonCurrentLiabilitiesDueWithinOneYear , otherCurrentLiabilities");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{shortTermLoan} , #{transactionalFinancialLiabilities} , #{billsPayable}");
			INTO_VALUES("#{accountsPayable} , #{advancePayment} , #{payrollPayable} , #{taxesPayable} , #{interestPayable} , #{dividendPayable}");
			INTO_VALUES("#{otherPayables} , #{nonCurrentLiabilitiesDueWithinOneYear} , #{otherCurrentLiabilities}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	/**
	 * 修改数据
	 * @param currentLiabilities
	 * @return
	 */
	public String updateCurrentLiabilities(CurrentLiabilities currentLiabilities) {
		return new SQL() {{
			UPDATE("t_currentLiabilities");
			SET("dataTime = #{dataTime} , shortTermLoan = #{shortTermLoan} , transactionalFinancialLiabilities = #{transactionalFinancialLiabilities}");
			SET("billsPayable = #{billsPayable} , accountsPayable = #{accountsPayable} , advancePayment = #{advancePayment} , payrollPayable = #{payrollPayable}");
			SET("taxesPayable = #{taxesPayable} , interestPayable = #{interestPayable} , dividendPayable = #{dividendPayable} , otherPayables = #{otherPayables}");
			SET("nonCurrentLiabilitiesDueWithinOneYear = #{nonCurrentLiabilitiesDueWithinOneYear} , otherCurrentLiabilities = #{otherCurrentLiabilities}");
			SET("updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 删除数据
	 * @param currentLiabilities
	 * @return
	 */
	public String deleteCurrentLiabilities(CurrentLiabilities currentLiabilities) {
		return new SQL() {{
			UPDATE("t_currentLiabilities");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectCurrentLiabilities(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , shortTermLoan , transactionalFinancialLiabilities , billsPayable");
			SELECT("accountsPayable , advancePayment , payrollPayable , taxesPayable , interestPayable , dividendPayable");
			SELECT("otherPayables , nonCurrentLiabilitiesDueWithinOneYear , otherCurrentLiabilities");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_currentLiabilities");
			WHERE("del = '0' and companyStockId = #{companyStockId}");
			ORDER_BY("dataTime DESC");
		}}.toString();
	}
}
