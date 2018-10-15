package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;

public interface UserService {
	
	
	
	/**
	 * 添加数据
	 * @param user
	 * @return
	 */
	ResultBean<Object> insertUser(User user) throws Exception;
	
	
	
	/**
	 * 修改密码
	 * @param token
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception 
	 */
	ResultBean<Object> updateUserPassword(String token , String oldPassword , String newPassword) throws Exception;
	
	
	
	/**
	 * 验证用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	ResultBean<Object> verifyLogin(String username , String password) throws Exception;

}
