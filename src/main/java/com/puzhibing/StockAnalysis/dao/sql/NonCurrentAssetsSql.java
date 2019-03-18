package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.NonCurrentAssets;

import java.util.Date;

public class NonCurrentAssetsSql {

	
	/**
	 * 添加数据
	 * @param nonCurrentAssets
	 * @return
	 */
	public String insertNonCurrentAssets(NonCurrentAssets nonCurrentAssets) {
		return new SQL() {{
			INSERT_INTO("t_noncurrentassets");
			INTO_COLUMNS("id , companyStockId , dataTime , afsfa , haei , ltr , ltbi , ire , fixedAssets , cap , engineerMaterial");
			INTO_COLUMNS("fac , pba , gasolineAssets , intangibleAssets , de , goodwill , ltpe , dta , onca , tnca");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{afsfa} , #{haei} , #{ltr} , #{ltbi} , #{ire} , #{fixedAssets} , #{cap} , #{engineerMaterial}");
			INTO_VALUES("#{fac} , #{pba} , #{gasolineAssets} , #{intangibleAssets} , #{de} , #{goodwill} , #{ltpe} , #{dta} , #{onca} , #{tnca}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentAssets
	 * @return
	 */
	public String updateNonCurrentAssets(NonCurrentAssets nonCurrentAssets) {
		return new SQL() {{
			UPDATE("t_noncurrentassets");
			SET("companyStockId = #{companyStockId} , dataTime = #{dataTime} , afsfa = #{afsfa} , haei = #{haei} , ltr = #{ltr} , ltbi = #{ltbi}");
			SET("ire = #{ire} , fixedAssets = #{fixedAssets} , cap = #{cap} , engineerMaterial = #{engineerMaterial} , fac = #{fac} , pba = #{pba}");
			SET("gasolineAssets = #{gasolineAssets} , intangibleAssets = #{intangibleAssets} , de = #{de} , goodwill = #{goodwill} , ltpe = #{ltpe} , dta = #{dta} , onca = #{onca} , tnca = #{tnca}");
			SET("updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentAssets
	 * @return
	 */
	public String deleteNonCurrentAssets(NonCurrentAssets nonCurrentAssets) {
		return new SQL() {{
			UPDATE("t_noncurrentassets");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectNonCurrentAssetsByCompanyStockId(String companyStockId , Date startTime , Date endTime) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , afsfa , haei , ltr , ltbi , ire , fixedAssets , cap , engineerMaterial");
			SELECT("fac , pba , gasolineAssets , intangibleAssets , de , goodwill , ltpe , dta , onca , tnca");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_noncurrentassets");
			if(null != startTime && null != endTime){
				WHERE("del = '0' and companyStockId = #{param1} and dataTime BETWEEN #{param2} AND #{param3}");
			}else {
				WHERE("del = '0' and companyStockId = #{companyStockId}");
			}
			ORDER_BY("dataTime DESC");
		}}.toString();
	} 
}
