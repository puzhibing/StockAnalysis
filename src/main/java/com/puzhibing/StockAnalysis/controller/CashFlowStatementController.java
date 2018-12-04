package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.CashFlowStatement;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CashFlowStatementService;

@RestController
public class CashFlowStatementController {
	
	@Autowired
	private CashFlowStatementService cashFlowStatementServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 添加数据
	 * @param cashFlowStatement
	 * @param currencyUnit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCashFlowStatement")
	public ResultBean<Object> insertCashFlowStatement(CashFlowStatement cashFlowStatement
			, String currencyUnit, String token){
		try {
			cashFlowStatementServiceImpl.insertCashFlowStatement(cashFlowStatement
					, currencyUnit, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	
	
}
