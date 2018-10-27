package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.NonCurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.NonCurrentLiabilitiesService;

@RestController
public class NonCurrentLiabilitiesController {

	@Autowired
	private NonCurrentLiabilitiesService nonCurrentLiabilitiesServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	
	/**
	 * 添加数据
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertNonCurrentLiabilities")
	public ResultBean<Object> insertNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities , String currencyUnit , String token){
		try {
			resultBean = nonCurrentLiabilitiesServiceImpl.insertNonCurrentLiabilities(nonCurrentLiabilities , currencyUnit , token);
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
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateNonCurrentLiabilities")
	public ResultBean<Object> updateNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities, String token){
		try {
			resultBean = nonCurrentLiabilitiesServiceImpl.updateNonCurrentLiabilities(nonCurrentLiabilities, token);
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
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteNonCurrentLiabilities")
	public ResultBean<Object> deleteNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities, String token){
		try {
			resultBean = nonCurrentLiabilitiesServiceImpl.deleteNonCurrentLiabilities(nonCurrentLiabilities, token);
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
	@RequestMapping(value = "/selectNonCurrentLiabilitiesByCompanyStockId")
	public ResultBean<Object> selectNonCurrentLiabilitiesByCompanyStockId(String companyStockId){
		try {
			resultBean = nonCurrentLiabilitiesServiceImpl.selectNonCurrentLiabilitiesByCompanyStockId(companyStockId);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
