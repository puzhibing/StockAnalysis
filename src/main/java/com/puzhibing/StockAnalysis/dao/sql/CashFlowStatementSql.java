package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CashFlowStatement;

public class CashFlowStatementSql {

	public String insertCashFlowStatement(CashFlowStatement cashFlowStatement) {
		return new SQL() {{
			INSERT_INTO("t_cashflowstatement");
			INTO_COLUMNS("id , companyStockId , dataTime , np , aip , dofagadadopba , aoia , aoltpe , loiffaiaaolta , losofa");
			INTO_COLUMNS("lofvc , fc , ll , ditad , iiiditl , lr , diori , iiopi , other , ncffoac , dtc , scbdwoy , flofa");
			INTO_COLUMNS("cateotp , iboc , eboce , iboce , niicace");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} #{np} , #{aip} , #{dofagadadopba} , #{aoia} , #{aoltpe} , #{loiffaiaaolta}");
			INTO_VALUES("#{losofa} , #{lofvc} , #{fc} , #{ll} , #{ditad} , #{iiiditl} , #{lr} , #{diori} , #{iiopi} , #{other}");
			INTO_VALUES("#{ncffoac} , #{dtc} , #{scbdwoy} , #{flofa} , #{cateotp} , #{iboc} , #{eboce} , #{iboce} , #{niicace}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	public String selectCashFlowStatement(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , np , aip , dofagadadopba , aoia , aoltpe , loiffaiaaolta , losofa");
			SELECT("lofvc , fc , ll , ditad , iiiditl , lr , diori , iiopi , other , ncffoac , dtc , scbdwoy , flofa");
			SELECT("cateotp , iboc , eboce , iboce , niicace");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_cashflowstatement");
			WHERE("companyStockId = #{companyStockId} and del = '0'");
		}}.toString();
	}
}
