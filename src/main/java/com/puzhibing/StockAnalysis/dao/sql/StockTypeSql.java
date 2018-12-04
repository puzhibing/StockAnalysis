package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.StockType;

public class StockTypeSql {

	
	/**
	 * 添加数据
	 * @param stockType
	 * @return
	 */
	public String insertStockType(StockType stockType) {
		return new SQL() {{
			INSERT_INTO("t_stocktype");
			INTO_COLUMNS("id , name , del , insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{name} , #{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	/**
	 * 修改数据
	 * @param stockType
	 * @return
	 */
	public String updateStockType(StockType stockType){
		return new SQL() {{
			UPDATE("t_stocktype");
			SET("name = #{name} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 删除数据
	 * @param stockType
	 * @return
	 */
	public String deleteStockType(StockType stockType){
		return new SQL() {{
			UPDATE("t_stocktype");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public String selectStockTypeById(String id) {
		return new SQL() {{
			SELECT("id , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_stocktype");
			WHERE("del = '0' and id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public String selectAllStockType(){
		return new SQL() {{
			SELECT("id , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_stocktype");
			WHERE("del = '0'");
			ORDER_BY("name");
		}}.toString();
	}
}
