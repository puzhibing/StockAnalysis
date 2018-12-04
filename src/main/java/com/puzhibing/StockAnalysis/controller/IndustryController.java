package com.puzhibing.StockAnalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.Industry;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.IndustryService;


@RestController
public class IndustryController {

	@Autowired
	private IndustryService industryServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	/**
	 * 添加数据
	 * @param industry
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertIndustry")
	public ResultBean<Object> insertIndustry(Industry industry, String token){
		resultBean = new ResultBean<Object>();
		if(null != industry && !StringUtils.isEmpty(token)) {
			try {
				resultBean = industryServiceImpl.insertIndustry(industry, token);
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
			}
		}else {
			resultBean.setB(false);
		}
		return resultBean;
	}
	
	
	/**
	 * 修改数据
	 * @param industry
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateIndustry")
	public ResultBean<Object> updateIndustry(Industry industry, String token){
		resultBean = new ResultBean<Object>();
		if(null != industry && !StringUtils.isEmpty(token)) {
			try {
				resultBean = industryServiceImpl.updateIndustry(industry, token);
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
			}
		}else {
			resultBean.setB(false);
		}
		return resultBean;
	}
	
	
	
	/**
	 * 删除数据
	 * @param industry
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteIndustry")
	public ResultBean<Object> deleteIndustry(Industry industry, String token){
		resultBean = new ResultBean<Object>();
		if(null != industry && !StringUtils.isEmpty(token)) {
			try {
				resultBean = industryServiceImpl.deleteIndustry(industry, token);
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setB(false);
			}
		}else {
			resultBean.setB(false);
		}
		return resultBean;
	}
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@RequestMapping(value = "/selectAllIndustry")
	public ResultBean<List<Industry>> selectAllIndustry(){
		ResultBean<List<Industry>> resultBean = new ResultBean<>();
		try {
			resultBean = industryServiceImpl.selectAllIndustry();
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setB(false);
		}
		return resultBean;
	}
}
