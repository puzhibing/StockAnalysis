package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.Industry;

public class IndustrySql {

	/**
	 * 添加数据
	 * @param industry
	 * @return
	 */
	public String insertIndustry(Industry industry) {
		return new SQL() {{
			INSERT_INTO("t_industry");
			INTO_COLUMNS("id , parentId , code , name , del , insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{parentId} , #{code}, #{name}, #{del}, #{insertUserId}, #{insertTime}, #{updateUserId}, #{updateTime}");
		}}.toString();
	}
	
	
	/**
	 * 根据code查询数据
	 * @param code
	 * @return
	 */
	public String selectIndustryByCode(String code) {
		return new SQL() {{
			SELECT("id , parentId , code , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_industry");
			WHERE("code = #{code} and del = '0'");
		}}.toString();
	}

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public String selectIndustryById(String id) {
		return new SQL() {{
			SELECT("id , parentId , code , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_industry");
			WHERE("id = #{id} and del = '0'");
		}}.toString();
	}


	/**
	 * 修改数据
	 * @param industry
	 * @return
	 */
	public String updateIndustry(Industry industry) {
		return new SQL() {{
			UPDATE("t_industry");
			SET("code = #{code}, name = #{name}, updateUserId = #{updateUserId}, updateTime = #{updateTime}");
			WHERE("id = #{id} and del = '0'");
		}}.toString();
	}
	
	
	
	/**
	 * 删除数据
	 * @param industry
	 * @return
	 */
	public String deleteIndustry(Industry industry) {
		return new SQL() {{
			UPDATE("t_industry");
			SET("del = '-1', updateUserId = #{updateUserId}, updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public String selectAllIndustry() {
		return new SQL() {{
			SELECT("id , parentId , code , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_industry");
			WHERE("del = '0'");
			ORDER_BY("code");
		}}.toString();
	}


	/**
	 * 根据父类id查询数据
	 * @param parentId
	 * @return
	 */
	public String selectDataByParentId(String parentId) {
		return new SQL() {{
			SELECT("id , parentId , code , name , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_industry");
			WHERE("del = '0' and parentId = #{parentId}");
			ORDER_BY("code");
		}}.toString();
	}
}
