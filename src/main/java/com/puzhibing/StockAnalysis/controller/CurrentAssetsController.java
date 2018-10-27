package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.CurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CurrentAssetsService;



@RestController
public class CurrentAssetsController {

	@Autowired
	private CurrentAssetsService currentAssetsServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param currentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCurrentAssets")
	public ResultBean<Object> insertCurrentAssets(CurrentAssets currentAssets , String currencyUnit , String token){
		try {
			resultBean = currentAssetsServiceImpl.insertCurrentAssets(currentAssets , currencyUnit , token);
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
	 * @param currentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateCurrentAssets")
	public ResultBean<Object> updateCurrentAssets(CurrentAssets currentAssets , String token){
		try {
			resultBean = currentAssetsServiceImpl.updateCurrentAssets(currentAssets, token);
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
	 * @param currentAssets
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteCurrentAssets")
	public ResultBean<Object> deleteCurrentAssets(CurrentAssets currentAssets , String token){
		try {
			resultBean = currentAssetsServiceImpl.deleteCurrentAssets(currentAssets, token);
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
	@RequestMapping(value = "/selectCurrentAssetsBycompanyStockId")
	public ResultBean<Object> selectCurrentAssetsBycompanyStockId(String companyStockId){
		try {
			resultBean = currentAssetsServiceImpl.selectCurrentAssetsBycompanyStockId(companyStockId);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		
		return resultBean;
	}
}
