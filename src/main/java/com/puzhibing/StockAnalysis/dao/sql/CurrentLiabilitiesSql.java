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
			INSERT_INTO("t_currentliabilities");
			INTO_COLUMNS("id , companyStockId , dataTime , stl , tfl , billsPayable , accountsPayable , advancePayment , payrollPayable");
			INTO_COLUMNS("taxesPayable , interestPayable , dividendPayable , otherPayables , nldwoy , ocl , tcl");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{stl} , #{tfl} , #{billsPayable} , #{accountsPayable} , #{advancePayment} , #{payrollPayable}");
			INTO_VALUES("#{taxesPayable} , #{interestPayable} , #{dividendPayable} , #{otherPayables} , #{nldwoy} , #{ocl} , #{tcl}");
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
			UPDATE("t_currentliabilities");
			SET("dataTime = #{dataTime} , stl = #{stl} , tfl = #{tfl} , billsPayable = #{billsPayable} , accountsPayable = #{accountsPayable}");
			SET("advancePayment = #{advancePayment} , payrollPayable = #{payrollPayable} , taxesPayable = #{taxesPayable} , interestPayable = #{interestPayable}");
			SET("dividendPayable = #{dividendPayable} , otherPayables = #{otherPayables} , nldwoy = #{nldwoy} , ocl = #{ocl} , tcl = #{tcl}");
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
			UPDATE("t_currentliabilities");
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
			SELECT("id , companyStockId , dataTime , stl , tfl , billsPayable , accountsPayable , advancePayment , payrollPayable");
			SELECT("taxesPayable , interestPayable , dividendPayable , otherPayables , nldwoy , ocl , tcl");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_currentliabilities");
			WHERE("del = '0' and companyStockId = #{companyStockId}");
			ORDER_BY("dataTime DESC");
		}}.toString();
	}
}
