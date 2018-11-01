package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CompanyStockMapper;
import com.puzhibing.StockAnalysis.pojo.CompanyStock;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CashFlowService;
import com.puzhibing.StockAnalysis.service.CompanyStockService;
import com.puzhibing.StockAnalysis.service.CurrentAssetsService;
import com.puzhibing.StockAnalysis.service.CurrentLiabilitiesService;
import com.puzhibing.StockAnalysis.service.NonCurrentAssetsService;
import com.puzhibing.StockAnalysis.service.NonCurrentLiabilitiesService;
import com.puzhibing.StockAnalysis.service.OwnersEquityChangeService;
import com.puzhibing.StockAnalysis.service.OwnersEquityService;
import com.puzhibing.StockAnalysis.service.ProfitService;
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
	
	@Autowired
	private CurrentAssetsService currentAssetsServiceImpl;
	
	@Autowired
	private NonCurrentAssetsService nonCurrentAssetsServiceImpl;
	
	@Autowired
	private CurrentLiabilitiesService currentLiabilitiesServiceImpl;
	
	@Autowired
	private NonCurrentLiabilitiesService nonCurrentLiabilitiesServiceImpl;
	
	@Autowired
	private OwnersEquityService ownersEquityServiceImpl;
	
	@Autowired
	private ProfitService profitServiceImpl;
	
	@Autowired
	private CashFlowService cashFlowServiceImpl;
	
	@Autowired
	private OwnersEquityChangeService ownersEquityChangeServiceImpl;

	
	
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
	public ResultBean<Object> deleteCompanyStock(String id, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(token) && StringUtils.isEmpty(id))) {
			User user = tokenutil.tokenToUser(token);

			try {
				CompanyStock companyStock = companyStockMapper.selectCompanyStockById(id);
				companyStock.setDel("-1");
				companyStock.setUpdateTime(new Date());
				companyStock.setUpdateUserId(user.getId());
	
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



	/**
	 * 根据编号模糊查询数据
	 * @param stockCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectCompanyStockLikeCode(String stockCode) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(stockCode)) {
			try {
				List<CompanyStock> list = companyStockMapper.selectCompanyStockLikeCode("%" + stockCode + "%");
				resultBean.setB(true);
				resultBean.setResult(list);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}




	
	@Override
	public ResultBean<Object> selectAllSecuritiesDataById(String id) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		Map<String, Object> map = new HashMap<>();
		if(!StringUtils.isEmpty(id)) {
			try {
				CompanyStock companyStock = companyStockMapper.selectCompanyStockById(id);
				ResultBean<Object> resultBean2 = currentAssetsServiceImpl.selectCurrentAssetsBycompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean3 = nonCurrentAssetsServiceImpl.selectNonCurrentAssetsByCompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean4 = currentLiabilitiesServiceImpl.selectCurrentLiabilities(companyStock.getId());
				ResultBean<Object> resultBean5 = nonCurrentLiabilitiesServiceImpl.selectNonCurrentLiabilitiesByCompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean6 = ownersEquityServiceImpl.selectOwnersEquityByCompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean7 = profitServiceImpl.selectProfitByCompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean8 = cashFlowServiceImpl.selectCashFlowByCompanyStockId(companyStock.getId());
				ResultBean<Object> resultBean9 = ownersEquityChangeServiceImpl.selectOwnersEquityChangeByCompanyStockId(companyStock.getId());
				map.put("currentAssets", resultBean2.getResult());
				map.put("nonCurrentAssets", resultBean3.getResult());
				map.put("currentLiabilities", resultBean4.getResult());
				map.put("nonCurrentLiabilities", resultBean5.getResult());
				map.put("ownersEquity", resultBean6.getResult());
				map.put("profit", resultBean7.getResult());
				map.put("cashFlow", resultBean8.getResult());
				map.put("ownersEquityChange", resultBean9.getResult());
				
				resultBean.setB(true);
				resultBean.setResult(map);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
