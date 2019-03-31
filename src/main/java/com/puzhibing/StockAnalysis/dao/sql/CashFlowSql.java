package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CashFlow;

import java.util.Date;


/**
 * 现金流
 * @author asus
 *
 */
public class CashFlowSql {

	
	/**
	 * 添加数据
	 * @param cashFlow
	 * @return
	 */
	public String insertCashFlow(CashFlow cashFlow) {
		return new SQL() {{
			INSERT_INTO("t_cashflow");
			INTO_COLUMNS("id , companyStockId , dataTime , crfsogas , ort , cortbar , ciioa , cpfgas , cpteapte , poatot , poocrtba , cffoa , ncffoa");
			INTO_COLUMNS("crfir , crfii , ncidofaiaaoaitpp , ncrfdosaobu , ocrtiahbr , cioia , cofaiaaocpfpi , cpfi , ncpbsaobu , poocrtia");
			INTO_COLUMNS("cfooia , ncffia , crfi , crfb , crftiob , ocrtfar , ciofa , cfdr , cpfrob , cpfdpoip , poocrtfa , cfoofa , ncfgbfra , teoerfocace");
			INTO_COLUMNS("niicace , caceatboty , bocaceaeot");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{crfsogas} , #{ort} , #{cortbar} , #{ciioa} , #{cpfgas} , #{cpteapte} , #{poatot} , #{poocrtba} , #{cffoa} , #{ncffoa}");
			INTO_VALUES("#{crfir} , #{crfii} , #{ncidofaiaaoaitpp} , #{ncrfdosaobu} , #{ocrtiahbr} , #{cioia} , #{cofaiaaocpfpi} , #{cpfi} , #{ncpbsaobu} , #{poocrtia}");
			INTO_VALUES("#{cfooia} , #{ncffia} , #{crfi} , #{crfb} , #{crftiob} , #{ocrtfar} , #{ciofa} , #{cfdr} , #{cpfrob} , #{cpfdpoip} , #{poocrtfa} , #{cfoofa} , #{ncfgbfra} , #{teoerfocace}");
			INTO_VALUES("#{niicace} , #{caceatboty} , #{bocaceaeot}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectCashFlowByCompanyStockId(String companyStockId, Date startTime, Date endTime) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , crfsogas , ort , cortbar , ciioa , cpfgas , cpteapte , poatot , poocrtba , cffoa , ncffoa");
			SELECT("crfir , crfii , ncidofaiaaoaitpp , ncrfdosaobu , ocrtiahbr , cioia , cofaiaaocpfpi , cpfi , ncpbsaobu , poocrtia");
			SELECT("cfooia , ncffia , crfi , crfb , crftiob , ocrtfar , ciofa , cfdr , cpfrob , cpfdpoip , poocrtfa , cfoofa , ncfgbfra , teoerfocace");
			SELECT("niicace , caceatboty , bocaceaeot");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_cashflow");
			if(null != startTime && null != endTime){
				WHERE("del = '0' and companyStockId = #{param1} and dataTime BETWEEN #{param2} AND #{param3}");
			}else {
				WHERE("del = '0' and companyStockId = #{param1}");
			}
		}}.toString();
	}
}
