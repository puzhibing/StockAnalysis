package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.CashFlow;


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
			INSERT_INTO("t_cashFlow");
			INTO_COLUMNS("id , companyStockId , dataTime , crfsogas , ort , cortbar , ciioa , cpfgas , poatot , poocrtba , cffoa , ncffoa");
			INTO_COLUMNS("crfir , crfii , ncidofaiaaoaitpp , ncrfdosaobu , ocrtiahbr , cioia , cofaiaaocpfpi , cpfi , ncpbsaobu , poocrtia");
			INTO_COLUMNS("cfooia , ncffia , crfi , crfb , ocrtfar , ciofa , cfdr , cpfdpoip , poocrtfa , cfoofa , ncfgbfra , np , aip , dofagadadopba");
			INTO_COLUMNS("aoia , aoltpe , loiffaiaaolta , losofa , lofvc , fc , ll , ditad , iiiditl , lr , diori , iiopi , other");
			INTO_COLUMNS("ncffoac , dtc , scbdwoy , flofa , cateotp , iboc , eboce , iboce , caceatboty , bocaceaeot , niicace , teoerfocace");
			INTO_COLUMNS("del , insertUserId , insertTime , updateUserId , updateTime");
			
			INTO_VALUES("#{id} , #{companyStockId} , #{dataTime} , #{crfsogas} , #{ort} , #{cortbar} , #{ciioa} , #{cpfgas} , #{poatot} , #{poocrtba} , #{cffoa} , #{ncffoa}");
			INTO_VALUES("#{crfir} , #{crfii} , #{ncidofaiaaoaitpp} , #{ncrfdosaobu} , #{ocrtiahbr} , #{cioia} , #{cofaiaaocpfpi} , #{cpfi} , #{ncpbsaobu} , #{poocrtia}");
			INTO_VALUES("#{cfooia} , #{ncffia} , #{crfi} , #{crfb} , #{ocrtfar} , #{ciofa} , #{cfdr} , #{cpfdpoip} , #{poocrtfa} , #{cfoofa} , #{ncfgbfra} , #{np} , #{aip} , #{dofagadadopba}");
			INTO_VALUES("#{aoia} , #{aoltpe} , #{loiffaiaaolta} , #{losofa} , #{lofvc} , #{fc} , #{ll} , #{ditad} , #{iiiditl} , #{lr} , #{diori} , #{iiopi} , #{other}");
			INTO_VALUES("#{ncffoac} , #{dtc} , #{scbdwoy} , #{flofa} , #{cateotp} , #{iboc} , #{eboce} , #{iboce} , #{caceatboty} , #{bocaceaeot} , #{niicace} , #{teoerfocace}");
			INTO_VALUES("#{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	public String selectCashFlowByCompanyStockId(String companyStockId) {
		return new SQL() {{
			SELECT("id , companyStockId , dataTime , crfsogas , ort , cortbar , ciioa , cpfgas , poatot , poocrtba , cffoa , ncffoa");
			SELECT("crfir , crfii , ncidofaiaaoaitpp , ncrfdosaobu , ocrtiahbr , cioia , cofaiaaocpfpi , cpfi , ncpbsaobu , poocrtia");
			SELECT("cfooia , ncffia , crfi , crfb , ocrtfar , ciofa , cfdr , cpfdpoip , poocrtfa , cfoofa , ncfgbfra , np , aip , dofagadadopba");
			SELECT("aoia , aoltpe , loiffaiaaolta , losofa , lofvc , fc , ll , ditad , iiiditl , lr , diori , iiopi , other");
			SELECT("ncffoac , dtc , scbdwoy , flofa , cateotp , iboc , eboce , iboce , caceatboty , bocaceaeot , niicace , teoerfocace");
			SELECT("del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_cashFlow");
			WHERE("companyStockId = #{companyStockId} and del = '0'");
		}}.toString();
	}
}
