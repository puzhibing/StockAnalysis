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
			INTO_COLUMNS("id , companyStockId , dataTime , businessIncome , interestIncome , earnedPremium , faci , toi , operatingCost , btaa , sellingExpenses , managementCost , financialCost");
			INTO_COLUMNS("ail , toc , fvci , adi , ifi , iiojvajv , oii , exchangeGains , otherIncome , operatingProfit , noi , noe , totalProfit , ite , netProfit , natfoci , tci , eps , beps , deps");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{businessIncome} , #{interestIncome} , #{earnedPremium} , #{faci} , #{toi} , #{operatingCost} , #{btaa} , #{sellingExpenses} , #{managementCost} , #{financialCost}");
			INTO_VALUES("#{ail} , #{toc} , #{fvci} , #{adi} , #{ifi} , #{iiojvajv} , #{oii} , #{exchangeGains} , #{otherIncome} , #{operatingProfit} , #{noi} , #{noe} , #{totalProfit} , #{ite} , #{netProfit} , #{natfoci} , #{tci} , #{eps} , #{beps} , #{deps}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectProfitByCompanyStockId(String companyStockId){
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , businessIncome , interestIncome , earnedPremium , faci , toi , operatingCost , btaa , sellingExpenses , managementCost , financialCost");
			SELECT("ail , toc , fvci , adi , ifi , iiojvajv , oii , exchangeGains , otherIncome , operatingProfit , noi , noe , totalProfit , ite , netProfit , natfoci , tci , eps , beps , deps");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_profit");
			WHERE("companyStockId = #{companyStockId} and del = '0'");
		}}.toString();
	}
}
