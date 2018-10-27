package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.Company;

public class CompanySql {
	
	
	/**
	 * 添加数据
	 * @param company
	 * @return
	 */
	public String insertCompany(Company company) {
		return new SQL() {{
			INSERT_INTO("t_company");
			INTO_COLUMNS("id , chName , chShortName , enName , enShortName , registerTime , url , del");
			INTO_COLUMNS("insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{chName} , #{chShortName} , #{enName} , #{enShortName} , #{registerTime}");
			INTO_VALUES("#{url} , #{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 修改数据
	 * @param company
	 * @return
	 */
	public String updateCompany(Company company) {
		return new SQL() {{
			UPDATE("t_company");
			SET("chName = #{chName} , chShortName = #{chShortName} , enName = #{enName} , enShortName = #{enShortName}");
			SET("registerTime = #{registerTime} , url = #{url} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 删除数据
	 * @param company
	 * @return
	 */
	public String deleteCompany(Company company) {
		return new SQL() {{
			UPDATE("t_company");
			SET("del = '-1' , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 查询所有企业数据
	 * @return
	 */
	public String selectAllCompany() {
		return new SQL() {{
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , url , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_company");
			WHERE("del = '0'");
		}}.toString();
	}
	
	
	
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public String selectCompanyById(String id) {
		return new SQL() {{
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , url , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_company");
			WHERE("del = '0' and id = #{id}");
		}}.toString();
	}

}
