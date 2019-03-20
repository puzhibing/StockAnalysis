package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.Profit;

import java.util.Date;


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
	public String selectProfitByCompanyStockId(String companyStockId , Date startTime , Date endTime){
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , businessIncome , interestIncome , earnedPremium , faci , toi , operatingCost , btaa , sellingExpenses , managementCost , financialCost");
			SELECT("ail , toc , fvci , adi , ifi , iiojvajv , oii , exchangeGains , otherIncome , operatingProfit , noi , noe , totalProfit , ite , netProfit , natfoci , tci , eps , beps , deps");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_profit");
			if(null != startTime && null != endTime){
				WHERE("del = '0' and companyStockId = #{param1} and dataTime BETWEEN #{param2} AND #{param3}");
			}else {
				WHERE("del = '0' and companyStockId = #{param1}");
			}
		}}.toString();
	}
}
