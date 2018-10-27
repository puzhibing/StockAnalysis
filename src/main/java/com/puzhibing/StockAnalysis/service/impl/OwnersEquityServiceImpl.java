package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.OwnersEquityMapper;
import com.puzhibing.StockAnalysis.pojo.OwnersEquity;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.OwnersEquityService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;



/**
 * 所有者权益
 * @author asus
 *
 */
@Service
public class OwnersEquityServiceImpl implements OwnersEquityService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	@Autowired
	private OwnersEquityMapper ownersEquityMapper;
	
	private ResultBean<Object> resultBean;
	
	private User user;

	@Override
	public ResultBean<Object> insertOwnersEquity(OwnersEquity ownersEquity , String currencyUnit, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != ownersEquity && !(StringUtils.isEmpty(currencyUnit) && StringUtils.isEmpty(token))) {
			ownersEquity = (OwnersEquity)unitCalculationUtil.calculation(currencyUnit , ownersEquity , OwnersEquity.class);
			
			user = tokenutil.tokenToUser(token);
			ownersEquity.setId(uuidutil.getUUID());
			ownersEquity.setDel("0");
			ownersEquity.setInsertTime(new Date());
			ownersEquity.setInsertUserId(user.getId());
			try {
				ownersEquityMapper.insertOwnersEquity(ownersEquity);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
