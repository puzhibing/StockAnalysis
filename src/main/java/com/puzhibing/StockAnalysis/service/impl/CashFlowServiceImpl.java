package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.CashFlowMapper;
import com.puzhibing.StockAnalysis.pojo.CashFlow;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CashFlowService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;


/**
 * 现金流
 * @author asus
 *
 */
@Service
public class CashFlowServiceImpl implements CashFlowService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	@Autowired
	private CashFlowMapper cashFlowMapper;
	
	private ResultBean<Object> resultBean;
	
	private User user;
	

	
	/**
	 * 添加数据
	 * @param cashFlow
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertCashFlow(CashFlow cashFlow, String currencyUnit, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != cashFlow && !(StringUtils.isEmpty(currencyUnit) && StringUtils.isEmpty(token))) {
			cashFlow = (CashFlow)unitCalculationUtil.calculation(currencyUnit , cashFlow , CashFlow.class);
			
			user = tokenutil.tokenToUser(token);
			cashFlow.setId(uuidutil.getUUID());
			cashFlow.setDel("0");
			cashFlow.setInsertTime(new Date());
			cashFlow.setInsertUserId(user.getId());
			try {
				cashFlowMapper.insertCashFlow(cashFlow);
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
	public ResultBean<Object> selectCashFlowByCompanyStockId(String companyStockId) throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				List<CashFlow> list = cashFlowMapper.selectCashFlowByCompanyStockId(companyStockId);
				resultBean.setB(true);
				resultBean.setResult(list);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
