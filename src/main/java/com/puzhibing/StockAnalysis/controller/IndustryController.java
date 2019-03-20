package com.puzhibing.StockAnalysis.controller;

import java.util.List;

import com.puzhibing.StockAnalysis.utils.ResultBeanUtil;
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

	private ResultBeanUtil<List<Industry>> resultBeanUtilList;
	
	
	
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
	 * @param id
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteIndustry")
	public ResultBean<Object> deleteIndustry(String id, String token){
		resultBean = new ResultBean<Object>();
		if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(token)) {
			try {
				resultBean = industryServiceImpl.deleteIndustry(id, token);
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


	/**
	 * 根据父类id查询数据
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/selectDataByParentId")
	public ResultBeanUtil<List<Industry>> selectDataByParentId(String parentId){
		if(com.puzhibing.StockAnalysis.utils.StringUtils.isNotEmpty(parentId)){
			try {
				resultBeanUtilList = industryServiceImpl.selectDataByParentId(parentId);
			}catch (Exception e){
				e.printStackTrace();
				resultBeanUtilList = ResultBeanUtil.getResultBeanUtil("处理逻辑异常" , false);
			}
		}else{
			resultBeanUtilList = ResultBeanUtil.getResultBeanUtil("参数异常" , false);
		}
		return resultBeanUtilList;
	}
}
