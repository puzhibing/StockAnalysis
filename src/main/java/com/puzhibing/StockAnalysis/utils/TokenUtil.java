package com.puzhibing.StockAnalysis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.pojo.User;


/**
 * 定义操作token工具类
 * @author Administrator
 *
 */
@Component
public class TokenUtil {
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UUIDUtil uuidUtil;
	
	private String json;
	
	private String token;
	
	
	
	
	
	/**
	 * 用token在redis中取出数据并转为User对象
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public User tokenToUser(String token) throws Exception {
		
		try {
			json = (String)redisTemplate.opsForValue().get(token);
		} catch (Exception e) {
			throw e;
		}
		return JSON.parseObject(json, User.class);
	}
	
	
	
	/**
	 * 生成token 并将对象存入redis中
	 * @param user
	 * @return
	 */
	public String generateToken(User user) {
		json = JSON.toJSONString(user);
		token = uuidUtil.getUUID();
		try {
			redisTemplate.opsForValue().set(token, json);
		} catch (Exception e) {
			throw e;
		}
		return token;
	}

}
