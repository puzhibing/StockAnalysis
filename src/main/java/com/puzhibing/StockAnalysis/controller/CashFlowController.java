package com.puzhibing.StockAnalysis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.CashFlow;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CashFlowService;

/**
 * 现金流
 * @author asus
 *
 */
@RestController
public class CashFlowController {

	private CashFlowService cashFlowServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param cashFlow
	 * @param currencyUnit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCashFlow")
	public ResultBean<Object> insertCashFlow(CashFlow cashFlow, String currencyUnit, String token){
		try {
			resultBean = cashFlowServiceImpl.insertCashFlow(cashFlow, currencyUnit, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		
		return resultBean;
	}
}
