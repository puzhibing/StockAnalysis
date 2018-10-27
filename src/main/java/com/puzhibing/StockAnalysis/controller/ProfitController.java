package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.Profit;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.ProfitService;

/**
 * 利润
 * @author asus
 *
 */
@RestController
public class ProfitController {

	@Autowired
	private ProfitService profitServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 添加数据
	 * @param profit
	 * @param currencyUnit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertProfit")
	public ResultBean<Object> insertProfit(Profit profit , String currencyUnit , String token){
		try {
			resultBean = profitServiceImpl.insertProfit(profit, currencyUnit, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
