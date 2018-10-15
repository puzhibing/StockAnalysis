package com.puzhibing.StockAnalysis.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import com.puzhibing.StockAnalysis.pojo.User;

public class UserSql {

	/**
	 * 添加数据
	 * @param user
	 * @return
	 */
	public String insertUser(User user) {
		return new SQL() {{
			INSERT_INTO("t_user");
			INTO_COLUMNS("id , username , password , del , insertUserId , insertTime , updateUserId , updateTime");
			INTO_VALUES("#{id} , #{username} , #{password} , #{del} , #{insertUserId} , #{insertTime} , #{updateUserId} , #{updateTime}");
		}}.toString();
	}
	
	
	
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public String updateUserPassword(User user) {
		return new SQL() {{
			UPDATE("t_user");
			SET("password = #{password} , updateUserId = #{updateUserId} , updateTime = #{updateTime}");
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	/**
	 * 根据用户名查询用户数据
	 * @param username
	 * @return
	 */
	public String selectUserByUsername(String username) {
		return new SQL() {{
			SELECT("id , username , password , del , insertUserId , insertTime , updateUserId , updateTime");
			FROM("t_user");
			WHERE("del = '0' and username = #{username}");
		}}.toString();
	}
}
