package com.puzhibing.StockAnalysis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.puzhibing.StockAnalysis.dao.sql.UserSql;
import com.puzhibing.StockAnalysis.pojo.User;

public interface UserMapper {

	
	/**
	 * 添加数据
	 * @param user
	 */
	@InsertProvider(type = UserSql.class , method = "insertUser")
	void insertUser(User user);
	
	
	
	/**
	 * 修改密码
	 * @param user
	 */
	@UpdateProvider(type = UserSql.class , method = "updateUserPassword")
	void updateUserPassword(User user);
	
	
	
	
	/**
	 * 根据用户名查询所有用户
	 * @param username
	 * @return
	 */
	@SelectProvider(type = UserSql.class , method = "selectUserByUsername")
	List<User> selectUserByUsername(String username);
}
