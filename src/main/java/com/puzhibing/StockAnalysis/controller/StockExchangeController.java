package com.puzhibing.StockAnalysis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockExchange;
import com.puzhibing.StockAnalysis.service.StockExchangeService;

@RestController
public class StockExchangeController {

	
	@Autowired
	private StockExchangeService stockExchangeServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param stockExchange
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertStockExchange")
	public ResultBean<Object> insertStockExchange(StockExchange stockExchange, String token){
		try {
			resultBean = stockExchangeServiceImpl.insertStockExchange(stockExchange, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	
	
	/**
	 * 修改数据
	 * @param stockExchange
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateStockExchange")
	public ResultBean<Object> updateStockExchange(StockExchange stockExchange, String token){
		try {
			resultBean = stockExchangeServiceImpl.updateStockExchange(stockExchange, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 删除数据
	 * @param stockExchange
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteStockExchange")
	public ResultBean<Object> deleteStockExchange(StockExchange stockExchange, String token){
		try {
			resultBean = stockExchangeServiceImpl.deleteStockExchange(stockExchange, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@RequestMapping(value = "/selectAllStockExchange")
	public ResultBean<Object> selectAllStockExchange(){
		try {
			resultBean = stockExchangeServiceImpl.selectAllStockExchange();
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
