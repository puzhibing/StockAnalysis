package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.NonCurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.NonCurrentAssetsService;



@RestController
public class NonCurrentAssetsController {

	@Autowired
	private NonCurrentAssetsService nonCurrentAssetsServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertNonCurrentAssets")
	public ResultBean<Object> insertNonCurrentAssets(NonCurrentAssets nonCurrentAssets, String token){
		try {
			resultBean = nonCurrentAssetsServiceImpl.insertNonCurrentAssets(nonCurrentAssets, token);
		} catch (Exception e) {
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateNonCurrentAssets")
	public ResultBean<Object> updateNonCurrentAssets(NonCurrentAssets nonCurrentAssets, String token){
		try {
			resultBean = nonCurrentAssetsServiceImpl.updateNonCurrentAssets(nonCurrentAssets, token);
		} catch (Exception e) {
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	
	/**
	 * 删除数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteNonCurrentAssets")
	public ResultBean<Object> deleteNonCurrentAssets(NonCurrentAssets nonCurrentAssets, String token){
		try {
			resultBean = nonCurrentAssetsServiceImpl.deleteNonCurrentAssets(nonCurrentAssets, token);
		} catch (Exception e) {
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
	@RequestMapping(value = "/selectNonCurrentAssetsByCompanyStockId")
	public ResultBean<Object> selectNonCurrentAssetsByCompanyStockId(String companyStockId){
		try {
			resultBean = nonCurrentAssetsServiceImpl.selectNonCurrentAssetsByCompanyStockId(companyStockId);
		} catch (Exception e) {
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
