package com.puzhibing.StockAnalysis.dao.sql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			INTO_COLUMNS("id , chName , chShortName , enName , enShortName , registerTime , industry");
			INTO_COLUMNS("url , del , insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{chName} , #{chShortName} , #{enName} , #{enShortName} , #{registerTime} , #{industry}");
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
			SET("registerTime = #{registerTime} , industry = #{industry} , url = #{url} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
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
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , industry , url , del");
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
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , industry , url , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_company");
			WHERE("del = '0' and id = #{id}");
		}}.toString();
	}


	/**
	 * 根据行业id查询数据
	 * @param industry
	 * @return
	 */
	public String selectCompanyByIndustry(String industry) {
		return new SQL() {{
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , industry , url , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_company");
			WHERE("del = '0' and industry = #{industry}");
		}}.toString();
	}


	
	/**
	 * 根据名称动态查询英文名称或中文名称
	 * @param name
	 * @return
	 */
	public String selectCompanyLikeName(String name) {
		return new SQL() {{
			SELECT("id , chName , chShortName , enName , enShortName , registerTime , industry , url , del");
			SELECT("insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_company");
			
			Pattern p = Pattern.compile("[a-z,A-Z]");
			Matcher m = p.matcher(name);
			if(m.find()) {//英文查询
				WHERE("del = '0' and enName like #{name}");
			}else {//中文查询
				WHERE("del = '0' and chName like #{name}");
			}
			
			
		}}.toString();
	}

}
