package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.OwnersEquity;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.OwnersEquityService;


/**
 * 所有者权益
 * @author asus
 *
 */
@RestController
public class OwnersEquityController {
	
	@Autowired
	private OwnersEquityService ownersEquityServiceImpl;

	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 添加数据
	 * @param ownersEquity
	 * @param currencyUnit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertOwnersEquity")
	public ResultBean<Object> insertOwnersEquity(OwnersEquity ownersEquity , String currencyUnit, String token){
		try {
			resultBean = ownersEquityServiceImpl.insertOwnersEquity(ownersEquity, currencyUnit, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
