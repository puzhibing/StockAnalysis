package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.StockExchange;

public class StockExchangeSql {

	
	/**
	 * 添加数据
	 * @param stockExchange
	 * @return
	 */
	public String insertStockExchange(StockExchange stockExchange) {
		return new SQL() {{
			INSERT_INTO("t_stockExchange");
			INTO_COLUMNS("id , name , address , del , insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{name} , #{address} , #{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	/**
	 * 修改数据
	 * @param stockExchange
	 * @return
	 */
	public String updateStockExchange(StockExchange stockExchange) {
		return new SQL() {{
			UPDATE("t_stockExchange");
			SET("name = #{name} , address = #{address} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 删除数据
	 * @param stockExchange
	 * @return
	 */
	public String deleteStockExchange(StockExchange stockExchange) {
		return new SQL() {{
			UPDATE("t_stockExchange");
			SET("del = #{del} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public String selectStockExchangeById(String id){
		return new SQL() {{
			SELECT("id , name , address , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_stockExchange");
			WHERE("del = '0' and id = #{id}");
		}}.toString();
	}
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public String selectAllStockExchange() {
		return new SQL() {{
			SELECT("id , name , address , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_stockExchange");
			WHERE("del = '0'");
		}}.toString();
	}
	
}
