package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CurrentLiabilitiesMapper;
import com.puzhibing.StockAnalysis.pojo.CurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CurrentLiabilitiesService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;


@Service
public class CurrentLiabilitiesServiceImpl implements CurrentLiabilitiesService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private CurrentLiabilitiesMapper currentLiabilitiesMapper;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	private ResultBean<Object> resultBean;
	
	private User user;
	
	private List<CurrentLiabilities> list;
	

	
	
	/**
	 * 添加数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertCurrentLiabilities(CurrentLiabilities currentLiabilities , String currencyUnit , String token)
			throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentLiabilities && !(StringUtils.isEmpty(token) && StringUtils.isEmpty(currencyUnit))) {
			currentLiabilities = (CurrentLiabilities)unitCalculationUtil.calculation(currencyUnit , currentLiabilities , CurrentLiabilities.class);
			
//			user = tokenutil.tokenToUser(token);
			currentLiabilities.setId(uuidutil.getUUID());
			currentLiabilities.setDel("0");
			currentLiabilities.setInsertTime(new Date());
//			currentLiabilities.setInsertUserId(user.getId());
			try {
				currentLiabilitiesMapper.insertCurrentLiabilities(currentLiabilities);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	/**
	 * 修改数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateCurrentLiabilities(CurrentLiabilities currentLiabilities, String token)
			throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentLiabilities && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			currentLiabilities.setUpdateTime(new Date());
			currentLiabilities.setUpdateUserId(user.getId());
			try {
				currentLiabilitiesMapper.updateCurrentLiabilities(currentLiabilities);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	/**
	 * 删除数据
	 * @param currentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteCurrentLiabilities(CurrentLiabilities currentLiabilities, String token)
			throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentLiabilities && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			currentLiabilities.setUpdateTime(new Date());
			currentLiabilities.setUpdateUserId(user.getId());
			try {
				currentLiabilitiesMapper.updateCurrentLiabilities(currentLiabilities);
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
	public ResultBean<Object> selectCurrentLiabilities(String companyStockId) throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				list = currentLiabilitiesMapper.selectCurrentLiabilities(companyStockId , null , null);
				resultBean.setResult(JSON.toJSONString(list));
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
