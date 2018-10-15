package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	
	/**
	 * 添加数据
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insertUser")
	public ResultBean<Object> insertUser(User user){
		try {
			resultBean = userServiceImpl.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	/**
	 * 修改密码
	 * @param token
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value = "/updateUserPassword")
	public ResultBean<Object> updateUserPassword(String token , String oldPassword , String newPassword){
		try {
			resultBean = userServiceImpl.updateUserPassword(token , oldPassword , newPassword);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	
	/**
	 * 认证登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/verifyLogin")
	public ResultBean<Object> verifyLogin(String username, String password){
		try {
			resultBean = userServiceImpl.verifyLogin(username , password);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}

}
