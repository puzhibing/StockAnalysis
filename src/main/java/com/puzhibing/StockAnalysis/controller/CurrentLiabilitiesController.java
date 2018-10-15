package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CurrentLiabilitiesService;

@RestController
public class CurrentLiabilitiesController {

	@Autowired
	private CurrentLiabilitiesService currentLiabilitiesServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCurrentLiabilities")
	public ResultBean<Object> insertCurrentLiabilities(CurrentLiabilities currentLiabilities, String token){
		try {
			resultBean = currentLiabilitiesServiceImpl.insertCurrentLiabilities(currentLiabilities, token);
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
	 * @param currentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateCurrentLiabilities")
	public ResultBean<Object> updateCurrentLiabilities(CurrentLiabilities currentLiabilities, String token){
		try {
			resultBean = currentLiabilitiesServiceImpl.updateCurrentLiabilities(currentLiabilities, token);
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
	 * @param currentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteCurrentLiabilities")
	public ResultBean<Object> deleteCurrentLiabilities(CurrentLiabilities currentLiabilities, String token){
		try {
			resultBean = currentLiabilitiesServiceImpl.deleteCurrentLiabilities(currentLiabilities, token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 */
	@RequestMapping(value = "/selectCurrentLiabilities")
	public ResultBean<Object> selectCurrentLiabilities(String companyStockId){
		try {
			resultBean = currentLiabilitiesServiceImpl.selectCurrentLiabilities(companyStockId);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
