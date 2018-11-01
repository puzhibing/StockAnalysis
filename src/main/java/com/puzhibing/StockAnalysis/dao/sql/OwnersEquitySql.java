package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.OwnersEquity;

/**
 * 所有者权益
 * @author asus
 *
 */
public class OwnersEquitySql {

	
	/**
	 * 添加数据
	 * @param ownersEquity
	 * @return
	 */
	public String insertOwnersEquity(OwnersEquity ownersEquity) {
		return new SQL() {{
			INSERT_INTO("t_ownersEquity");
			INTO_COLUMNS("id , companyStockId , dataTime , pic , capitalReserve , lts , surplusReserve , undistributedProfit , toe");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{pic} , #{capitalReserve} , #{lts} , #{surplusReserve} , #{undistributedProfit} , #{toe}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectOwnersEquityByCompanyStockId(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , pic , capitalReserve , lts , surplusReserve , undistributedProfit , toe");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_ownersEquity");
			WHERE("companyStockId = #{companyStockId} and del = '0'");
		}}.toString();
	}
}
