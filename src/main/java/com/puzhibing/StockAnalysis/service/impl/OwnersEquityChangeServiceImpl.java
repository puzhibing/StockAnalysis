package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puzhibing.StockAnalysis.dao.mapper.OwnersEquityChangeMapper;
import com.puzhibing.StockAnalysis.pojo.OwnersEquityChange;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.OwnersEquityChangeService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;


/**
 * 所有者权益变动
 * @author asus
 *
 */
@Service
public class OwnersEquityChangeServiceImpl implements OwnersEquityChangeService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	@Autowired
	private OwnersEquityChangeMapper ownersEquityChangeMapper;
	
	private ResultBean<Object> resultBean;
	
	private User user;

	
	/**
	 * 添加数据
	 * @param ownersEquityChange
	 * @param currencyUnit
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertOwnersEquityChange(OwnersEquityChange ownersEquityChange, String currencyUnit,
			String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != ownersEquityChange && !(StringUtils.isEmpty(currencyUnit) && StringUtils.isEmpty(token))) {
			ownersEquityChange = (OwnersEquityChange)unitCalculationUtil.calculation(currencyUnit , ownersEquityChange , OwnersEquityChange.class);
			
			user = tokenutil.tokenToUser(token);
			ownersEquityChange.setId(uuidutil.getUUID());
			ownersEquityChange.setDel("0");
			ownersEquityChange.setInsertTime(new Date());
			ownersEquityChange.setInsertUserId(user.getId());
			try {
				ownersEquityChangeMapper.insertOwnersEquityChange(ownersEquityChange);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
