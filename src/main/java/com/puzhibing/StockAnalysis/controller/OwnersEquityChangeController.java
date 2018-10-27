package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.OwnersEquityChange;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.OwnersEquityChangeService;

/**
 * 所有者权益变动
 * @author asus
 *
 */
@RestController
public class OwnersEquityChangeController {

	@Autowired
	private OwnersEquityChangeService ownersEquityChangeServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 添加数据
	 * @param ownersEquityChange
	 * @param currencyUnit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertOwnersEquityChange")
	public ResultBean<Object> insertOwnersEquityChange(OwnersEquityChange ownersEquityChange, String currencyUnit, String token){
		try {
			resultBean = ownersEquityChangeServiceImpl.insertOwnersEquityChange(ownersEquityChange, currencyUnit, token);
		} catch (Exception e) {
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
