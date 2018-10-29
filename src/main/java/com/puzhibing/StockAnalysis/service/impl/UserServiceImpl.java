package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.UserMapper;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.UserService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UserMapper userMapper;
	
	private String username;
	
	private String password;
	
	private User user;
	
	private String token;

	
	
	/**
	 * 添加数据
	 * @param user
	 * @return
	 */
	@Override
	public ResultBean<Object> insertUser(User user) {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != user) {
			username = user.getUsername();
			password = user.getPassword();
			if(!(StringUtils.isEmpty(username) && StringUtils.isEmpty(password))) {
				String id = uuidutil.getUUID();
				user.setId(id);
				user.setDel("0");
				user.setInsertTime(new Date());
				user.setInsertUserId(id);
				try {
					userMapper.insertUser(user);
					resultBean.setB(true);
				} catch (Exception e) {
					throw e;
				}
			}else {
				resultBean.setB(false);
				resultBean.setResult("用户名或密码无效");
			}
		}else {
			resultBean.setB(false);
			resultBean.setResult("请求数据异常");
		}
		return resultBean;
	}

	
	
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultBean<Object> updateUserPassword(String token , String oldPassword , String newPassword) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(token) && StringUtils.isEmpty(oldPassword) && StringUtils.isEmpty(newPassword))) {
			User user = tokenutil.tokenToUser(token);
			if(user.getPassword().equals(oldPassword)) {
				user.setPassword(newPassword);
				user.setUpdateTime(new Date());
				user.setUpdateUserId(user.getId());
				
				try {
					userMapper.updateUserPassword(user);
					resultBean.setB(true);
				} catch (Exception e) {
					throw e;
				}
				
			}else {
				resultBean.setB(false);
				resultBean.setResult("非法操作");
			}
			
			
		}else {
			resultBean.setB(false);
			resultBean.setResult("请求数据异常");
		}
		return resultBean;
	}

	
	
	/**
	 * 验证用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public ResultBean<Object> verifyLogin(String username, String password) {
		ResultBean<Object> resultBean = new ResultBean<>();
		boolean b = false;
		if(!StringUtils.isEmpty(username)) {
			try {
				List<User> list = userMapper.selectUserByUsername(username);
				if(null == list) {
					resultBean.setB(false);
					resultBean.setResult("用户名无效");
					return resultBean;
				}
				
				for (User user : list) {
					if(user.getPassword().equals(password)) {
						b = true;
						this.user = user;
						break;
					}
				}
				
				if(b) {
					token = tokenutil.generateToken(this.user);
					resultBean.setB(true);
					resultBean.setResult(token);
					
				}else {
					resultBean.setB(false);
					resultBean.setResult("密码错误");
				}
				
				
			} catch (Exception e) {
				throw e;
			}
		}else {
			resultBean.setB(false);
			resultBean.setResult("请求数据异常");
		}
		return resultBean;
	}

}
