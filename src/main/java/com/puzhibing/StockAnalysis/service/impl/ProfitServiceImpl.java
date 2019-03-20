package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.ProfitMapper;
import com.puzhibing.StockAnalysis.pojo.Profit;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.ProfitService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;



/**
 * 利润
 * @author asus
 *
 */
@Service
public class ProfitServiceImpl implements ProfitService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	@Autowired
	private ProfitMapper profitMapper;
	
	private ResultBean<Object> resultBean;
	
	private User user;
	
	
	
	
	/**
	 * 添加数据
	 * @param profit
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertProfit(Profit profit, String currencyUnit, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != profit && !(StringUtils.isEmpty(currencyUnit) && StringUtils.isEmpty(token))) {
			profit = (Profit)unitCalculationUtil.calculation(currencyUnit , profit , Profit.class);
			
			user = tokenutil.tokenToUser(token);
			profit.setId(uuidutil.getUUID());
			profit.setDel("0");
			profit.setInsertTime(new Date());
			profit.setInsertUserId(user.getId());
			try {
				profitMapper.insertProfit(profit);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}




	@Override
	public ResultBean<Object> selectProfitByCompanyStockId(String companyStockId) throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				List<Profit> list = profitMapper.selectProfitByCompanyStockId(companyStockId , null , null);
				resultBean.setB(true);
				resultBean.setResult(list);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
