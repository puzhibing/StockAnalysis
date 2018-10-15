package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockType;
import com.puzhibing.StockAnalysis.service.StockTypeService;



@RestController
public class StockTypeController {
	
	@Autowired
	private StockTypeService stockTypeServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param stockType
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertStockType")
	public ResultBean<Object> insertStockType(StockType stockType, String token){
		try {
			resultBean = stockTypeServiceImpl.insertStockType(stockType, token);
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
	 * @param stockType
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateStockType")
	public ResultBean<Object> updateStockType(StockType stockType, String token){
		try {
			resultBean = stockTypeServiceImpl.updateStockType(stockType, token);
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
	 * @param stockType
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteStockType")
	public ResultBean<Object> deleteStockType(StockType stockType, String token){
		try {
			resultBean = stockTypeServiceImpl.deleteStockType(stockType, token);
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
	@RequestMapping(value = "/selectAllStockType")
	public ResultBean<Object> selectAllStockType(){
		try {
			resultBean = stockTypeServiceImpl.selectAllStockType();
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}

}
