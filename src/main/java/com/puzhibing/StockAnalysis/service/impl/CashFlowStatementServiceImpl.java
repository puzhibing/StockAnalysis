package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.CashFlowStatementMapper;
import com.puzhibing.StockAnalysis.pojo.CashFlow;
import com.puzhibing.StockAnalysis.pojo.CashFlowStatement;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CashFlowStatementService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;

@Service
public class CashFlowStatementServiceImpl implements CashFlowStatementService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	@Autowired
	private CashFlowStatementMapper cashFlowStatementMapper;
	
	private ResultBean<Object> resultBean;
	
	private User user;

	
	
	/**
	 * 添加数据
	 * @param cashFlowStatement
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertCashFlowStatement(CashFlowStatement cashFlowStatement
			, String currencyUnit, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != cashFlowStatement && !(StringUtils.isEmpty(currencyUnit) 
				&& StringUtils.isEmpty(token))) {
			cashFlowStatement = (CashFlowStatement)unitCalculationUtil.calculation(currencyUnit 
					, cashFlowStatement , CashFlow.class);
			
			user = tokenutil.tokenToUser(token);
			cashFlowStatement.setId(uuidutil.getUUID());
			cashFlowStatement.setDel("0");
			cashFlowStatement.setInsertTime(new Date());
			cashFlowStatement.setInsertUserId(user.getId());
			try {
				cashFlowStatementMapper.insertCashFlowStatement(cashFlowStatement);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	/**
	 * 查询数据
	 * @param companyStockId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectCashFlowStatementByCompanyStockId(String companyStockId)
			throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				List<CashFlowStatement> list = cashFlowStatementMapper
						.selectCashFlowStatementByCompanyStockId(companyStockId);
				resultBean.setB(true);
				resultBean.setResult(list);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}
}
