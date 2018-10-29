package com.puzhibing.StockAnalysis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyMapper;
import com.puzhibing.StockAnalysis.pojo.Company;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CompanyService;
import com.puzhibing.StockAnalysis.service.CompanyStockService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;



@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private CompanyStockService companyStockServiceImpl;
	
	private User user;
	
	private ResultBean<Object> resultUtil;
	
	
	
	
	/**
	 * 添加数据
	 * @param company
	 * @param token
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public ResultBean<Object> insertCompany(Company company, String token) throws Exception {
		resultUtil = new ResultBean<>();
		if(null != company && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			String id = uuidutil.getUUID();
			company.setId(id);
			company.setDel("0");
			company.setInsertUserId(user.getId());
			company.setInsertTime(new Date());
			
			try {
				companyMapper.insertCompany(company);
				Company company2 = companyMapper.selectCompanyInfoById(id);
				resultUtil.setB(true);
				resultUtil.setResult(JSON.toJSONString(company2));
			} catch (Exception e) {
				throw e;
			}
		}
		return resultUtil;
	}



	/**
	 * 修改数据
	 * @param company
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateCompany(Company company, String token) throws Exception {
		resultUtil = new ResultBean<>();
		if(null != company && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			company.setUpdateTime(new Date());
			company.setUpdateUserId(user.getId());
			
			try {
				companyMapper.updateCompany(company);
				resultUtil.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultUtil;
	}



	
	/**
	 * 删除数据
	 * @param company
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional//开启事务
	public ResultBean<Object> deleteCompany(Company company, String token) throws Exception {
		
		/*
		 * 1.删除其他相关数据
		 * 2.删除主体数据
		 */
		resultUtil = new ResultBean<>();
		if(null != company && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			//删除股票数据
			ResultBean<Object> resultBean = companyStockServiceImpl.selectCompanyStockByCompanyId(company.getId());
			String json = (String)resultBean.getResult();
			List<CompanyStock> list = JSON.parseObject(json, new ArrayList<CompanyStock>().getClass());
			for (CompanyStock companyStock : list) {
				companyStockServiceImpl.deleteCompanyStock(companyStock.getId(), token);
			}
			
			//删除企业数据
			company.setDel("-1");
			company.setUpdateTime(new Date());
			company.setUpdateUserId(user.getId());
			try {
				companyMapper.deleteCompany(company);
				resultUtil.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultUtil;
	}



	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectAllCompany() throws Exception {
		ResultBean<Object> resultUtil = new ResultBean<>();
		try {
			List<Company> list = companyMapper.selectAllCompany();
			resultUtil.setB(true);
			resultUtil.setResult(list);
		} catch (Exception e) {
			throw e;
		}
		return resultUtil;
	}



	
	/**
	 * 查询单个企业详情数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectCompanyInfoById(String id) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(id)) {
			try {
				Company company = companyMapper.selectCompanyInfoById(id);
				resultBean.setB(true);
				resultBean.setResult(company);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
