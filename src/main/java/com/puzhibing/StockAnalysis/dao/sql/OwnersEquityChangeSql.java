package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.OwnersEquityChange;

/**
 * 所有者权益变动
 * @author asus
 *
 */
public class OwnersEquityChangeSql {

	
	public String insertOwnersEquityChange(OwnersEquityChange ownersEquityChange) {
		return new SQL() {{
			INSERT_INTO("t_ownersEquityChange");
			INTO_COLUMNS("id , companyStockId , dataTime , yebateoly , apc , eec , batboty , aoioditcy , np , galdiioe , ncifvoafsfa , tiociooraioiuuem");
			INTO_COLUMNS("itrtoei , other1 , oiarc , cibo , taospiitoe , other2 , pd , esr , dooos , other3 , itooe , csicocs , ssticocs , sstmufl , other4 , bateoty");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{yebateoly} , #{apc} , #{eec} , #{batboty} , #{aoioditcy} , #{np} , #{galdiioe} , #{ncifvoafsfa} , #{tiociooraioiuuem}");
			INTO_VALUES("#{itrtoei} , #{other1} , #{oiarc} , #{cibo} , #{taospiitoe} , #{other2} , #{pd} , #{esr} , #{dooos} , #{other3} , #{itooe} , #{csicocs} , #{ssticocs} , #{sstmufl} , #{other4} , #{bateoty}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectOwnersEquityChangeByCompanyStockId(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , yebateoly , apc , eec , batboty , aoioditcy , np , galdiioe , ncifvoafsfa , tiociooraioiuuem");
			SELECT("itrtoei , other1 , oiarc , cibo , taospiitoe , other2 , pd , esr , dooos , other3 , itooe , csicocs , ssticocs , sstmufl , other4 , bateoty");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_ownersEquityChange");
			WHERE("companyStockId = #{companyStockId} and del = '0'");
		}}.toString();
	}
}
