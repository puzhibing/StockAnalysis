package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CompanyStockService;



@RestController
public class CompanyStockController {
	
	@Autowired
	private CompanyStockService companyStockServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	/**
	 * 添加数据
	 * @param companyStock
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCompanyStock")
	public ResultBean<Object> insertCompanyStock(CompanyStock companyStock , String token){
		try {
			resultBean = companyStockServiceImpl.insertCompanyStock(companyStock, token);
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
	 * @param companyStock
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteCompanyStock")
	public ResultBean<Object> deleteCompanyStock(String id, String token){
		try {
			resultBean = companyStockServiceImpl.deleteCompanyStock(id, token);
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
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/selectCompanyStockByCompanyId")
	public ResultBean<Object> selectCompanyStockByCompanyId(String companyId){
		try {
			resultBean = companyStockServiceImpl.selectCompanyStockByCompanyId(companyId);
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
	 * @param companyStock
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateCompanyStock")
	public ResultBean<Object> updateCompanyStock(CompanyStock companyStock, String token){
		try {
			resultBean = companyStockServiceImpl.updateCompanyStock(companyStock , token);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
	
	
	
	
	/**
	 * 根据id查询所有数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectAllSecuritiesDataById")
	public ResultBean<Object> selectAllSecuritiesDataById(String id){
		try {
			resultBean = companyStockServiceImpl.selectAllSecuritiesDataById(id);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}


	/**
	 * 根据证券编号模糊查询企业数据和相关其他数据
	 * @param stockCode
	 * @return
	 */
	@RequestMapping(value = "/selectAllDataLikeCode")
	public ResultBean<Object> selectAllDataLikeCode(String stockCode){
		try {
			resultBean = companyStockServiceImpl.selectAllDataLikeCode(stockCode);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		return resultBean;
	}
}
