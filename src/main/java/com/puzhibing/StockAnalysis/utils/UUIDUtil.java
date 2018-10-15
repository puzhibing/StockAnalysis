package com.puzhibing.StockAnalysis.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * 生成唯一码的工具类
 * 
 * @author Administrator
 *
 */
@Component
public class UUIDUtil {

	public String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}
}
