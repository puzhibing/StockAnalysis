package com.puzhibing.StockAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.StockAnalysis.pojo.Company;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.CompanyService;


/**
 * 操作企业相关的接口类
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
public class CompanyController {
	
	@Autowired
	private CompanyService companyServiceImpl;
	
	private ResultBean<Object> resultBean;
	
	
	
	
	/**
	 * 添加数据
	 * @param company
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/insertCompany")
	public ResultBean<Object> insertCompany(Company company, String token){
		try {
			resultBean = companyServiceImpl.insertCompany(company, token);
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
	 * @param company
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/updateCompany")
	public ResultBean<Object> updateCompany(Company company, String token) {
		try {
			resultBean = companyServiceImpl.updateCompany(company, token);
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
	 * @param company
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deleteCompany")
	public ResultBean<Object> deleteCompany(Company company , String token) {
		try {
			resultBean = companyServiceImpl.deleteCompany(company, token);
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
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAllCompany")
	public ResultBean<Object> selectAllCompany() {
		try {
			resultBean = companyServiceImpl.selectAllCompany();
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		
		return resultBean;
	}
	
	
	
	/**
	 * 查询单个企业详情数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectCompanyInfoById")
	public ResultBean<Object> selectCompanyInfoById(String id) {
		try {
			resultBean = companyServiceImpl.selectCompanyInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean<>();
			resultBean.setB(false);
			resultBean.setResult("数据处理异常");
		}
		
		return resultBean;
	}

}
