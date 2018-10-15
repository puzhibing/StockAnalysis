package com.puzhibing.StockAnalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@MapperScan(value = "com.puzhibing.StockAnalysis.dao.mapper")
public class StockAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAnalysisApplication.class, args);
	}
}
