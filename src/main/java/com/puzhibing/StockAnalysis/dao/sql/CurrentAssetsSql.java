package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CurrentAssets;

import java.util.Date;

public class CurrentAssetsSql {

	
	
	/**
	 * 添加数据
	 * @param currentAssets
	 * @return
	 */
	public String insertCurrentAssets(CurrentAssets currentAssets) {
		return new SQL() {{
			INSERT_INTO("t_currentassets");
			INTO_COLUMNS("id , companyStockId , dataTime , moneyFunds , wof , tfa , dfa , bbrfa , billReceivable , accountsReceivable , prepayments");
			INTO_COLUMNS("interestReceivable , dividendReceivable , otherReceivables , stock , ncadwoy , oca , tca");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{moneyFunds} , #{wof} , #{tfa} , #{dfa} , #{bbrfa} , #{billReceivable} , #{accountsReceivable} , #{prepayments}");
			INTO_VALUES("#{interestReceivable} , #{dividendReceivable} , #{otherReceivables} , #{stock} , #{ncadwoy} , #{oca} , #{tca}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 修改数据
	 * @param currentAssets
	 * @return
	 */
	public String updateCurrentAssets(CurrentAssets currentAssets) {
		return new SQL() {{
			UPDATE("t_currentassets");
			SET("companyStockId = #{companyStockId} , dataTime = #{dataTime} , moneyFunds = #{moneyFunds} , wof = #{wof} , tfa = #{tfa} , dfa = #{dfa} , bbrfa = #{bbrfa}");
			SET("billReceivable = #{billReceivable} , accountsReceivable = #{accountsReceivable} , prepayments = #{prepayments} , interestReceivable = #{interestReceivable}");
			SET("dividendReceivable = #{dividendReceivable} , otherReceivables = #{otherReceivables} , stock = #{stock} , ncadwoy = #{ncadwoy}");
			SET("oca = #{oca} , tca = #{tca} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 删除数据
	 * @param currentAssets
	 * @return
	 */
	public String deleteCurrentAssets(CurrentAssets currentAssets) {
		return new SQL() {{
			UPDATE("t_currentassets");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 根据企业股票id查询数据，排序
	 * @param companyStockId
	 * @return
	 */
	public String selectCurrentAssetsBycompanyStockId(String companyStockId , Date startTime , Date endTime) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , moneyFunds , wof , tfa , dfa , bbrfa , billReceivable , accountsReceivable , prepayments");
			SELECT("interestReceivable , dividendReceivable , otherReceivables , stock , ncadwoy , oca , tca");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_currentassets");
			if(null != startTime && null != endTime){
				WHERE("del = '0' and companyStockId = #{param1} and dataTime BETWEEN #{param2} AND #{param3}");
			}else {
				WHERE("del = '0' and companyStockId = #{companyStockId}");
			}
			ORDER_BY("dataTime DESC");
		}}.toString();
	}
}
