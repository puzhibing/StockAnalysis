package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.Profit;


/**
 * 利润
 * @author asus
 *
 */
public class ProfitSql {

	
	/**
	 * 添加数据
	 * @param profit
	 * @return
	 */
	public String insertProfit(Profit profit){
		return new SQL() {{
			INSERT_INTO("t_profit");
			INTO_COLUMNS("id , companyStockId , dataTime , businessIncome , operatingCost , btaa , sellingExpenses , managementCost , financialCost");
			INTO_COLUMNS("ail , fvci , ifi , iiojvajv , oii , operatingProfit , noi , noe , paldoia , onoe , totalProfit , ite , netProfit , eps , beps , deps");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{businessIncome} , #{operatingCost} , #{btaa} , #{sellingExpenses} , #{managementCost} , #{financialCost}");
			INTO_VALUES("#{ail} , #{fvci} , #{ifi} , #{iiojvajv} , #{oii} , #{operatingProfit} , #{noi} , #{noe} , #{paldoia} , #{onoe} , #{totalProfit} , #{ite} , #{netProfit} , #{eps} , #{beps} , #{deps}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
}
