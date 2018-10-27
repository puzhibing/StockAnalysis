package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyStockMapper;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CompanyStockService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;


@Service
public class CompanyStockServiceImpl implements CompanyStockService {
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private CompanyStockMapper companyStockMapper;

	
	
	/**
	 * 添加数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultBean<Object> insertCompanyStock(CompanyStock companyStock , String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != companyStock && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			
			companyStock.setId(uuidutil.getUUID());
			companyStock.setDel("0");
			companyStock.setInsertTime(new Date());
			companyStock.setInsertUserId(user.getId());
			try {
				companyStockMapper.insertCompanyStock(companyStock);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	
	/**
	 * 删除数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteCompanyStock(CompanyStock companyStock, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != companyStock && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			
			companyStock.setDel("-1");
			companyStock.setUpdateTime(new Date());
			companyStock.setUpdateUserId(user.getId());
			try {
				companyStockMapper.deleteCompanyStock(companyStock);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}


	

	/**
	 * 根据企业id查询对应的所有数据
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectCompanyStockByCompanyId(String companyId) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		try {
			List<CompanyStock> list = companyStockMapper.selectCompanyStockByCompanyId(companyId);
			String json = JSON.toJSONString(list);
			resultBean.setB(true);
			resultBean.setResult(json);
		} catch (Exception e) {
			throw e;
		}
		return resultBean;
	}




	/**
	 * 修改数据
	 * @param companyStock
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateCompanyStock(CompanyStock companyStock, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != companyStock && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			
			companyStock.setUpdateTime(new Date());
			companyStock.setUpdateUserId(user.getId());
			try {
				companyStockMapper.updateCompanyStock(companyStock);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
