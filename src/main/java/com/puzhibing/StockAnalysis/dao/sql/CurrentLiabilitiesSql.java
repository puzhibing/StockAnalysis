package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;

import java.util.Date;

public class CurrentLiabilitiesSql {

	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 * @return
	 */
	public String insertCurrentLiabilities(CurrentLiabilities currentLiabilities) {
		return new SQL() {{
			INSERT_INTO("t_currentliabilities");
			INTO_COLUMNS("id , companyStockId , dataTime , stl , uf , cwstb , tfl , dfl , srfa , billsPayable , accountsPayable , advancePayment , payrollPayable");
			INTO_COLUMNS("taxesPayable , interestPayable , dividendPayable , otherPayables , nldwoy , ocl , tcl");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{stl} , #{uf} , #{cwstb} , #{tfl} , #{dfl} , #{srfa} , #{billsPayable} , #{accountsPayable} , #{advancePayment} , #{payrollPayable}");
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
			SET("dataTime = #{dataTime} , stl = #{stl} , uf = #{uf} , cwstb = #{cwstb} , tfl = #{tfl} , dfl = #{dfl} , srfa = ${srfa} , billsPayable = #{billsPayable} , accountsPayable = #{accountsPayable}");
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
	public String selectCurrentLiabilities(String companyStockId , Date startTime , Date endTime) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , stl , uf , cwstb , tfl , dfl , srfa , billsPayable , accountsPayable , advancePayment , payrollPayable");
			SELECT("taxesPayable , interestPayable , dividendPayable , otherPayables , nldwoy , ocl , tcl");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_currentliabilities");
			if(null != startTime && null != endTime){
				WHERE("del = '0' and companyStockId = #{param1} and dataTime BETWEEN #{param2} AND #{param3}");
			}else {
				WHERE("del = '0' and companyStockId = #{param1}");
			}
			ORDER_BY("dataTime DESC");
		}}.toString();
	}
}
