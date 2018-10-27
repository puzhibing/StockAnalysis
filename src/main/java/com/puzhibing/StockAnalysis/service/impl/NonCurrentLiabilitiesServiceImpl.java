package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.NonCurrentLiabilitiesMapper;
import com.puzhibing.StockAnalysis.pojo.NonCurrentLiabilities;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.NonCurrentLiabilitiesService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;

@Service
public class NonCurrentLiabilitiesServiceImpl implements NonCurrentLiabilitiesService {

	@Autowired
	private UUIDUtil uuidutil;

	@Autowired
	private TokenUtil tokenutil;

	@Autowired
	private NonCurrentLiabilitiesMapper nonCurrentLiabilitiesMapper;

	@Autowired
	private UnitCalculationUtil unitCalculationUtil;

	private User user;

	private ResultBean<Object> resultBean;

	private List<NonCurrentLiabilities> list;

	/**
	 * 添加数据
	 * 
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities, String currencyUnit, String token) throws Exception {
		resultBean = new ResultBean<>();
		if (null != nonCurrentLiabilities && !(StringUtils.isEmpty(token) && StringUtils.isEmpty(currencyUnit))) {
			nonCurrentLiabilities = (NonCurrentLiabilities) unitCalculationUtil.calculation(currencyUnit,
					nonCurrentLiabilities, NonCurrentLiabilities.class);

			user = tokenutil.tokenToUser(token);
			nonCurrentLiabilities.setId(uuidutil.getUUID());
			nonCurrentLiabilities.setDel("0");
			nonCurrentLiabilities.setInsertTime(new Date());
			nonCurrentLiabilities.setInsertUserId(user.getId());
			try {
				nonCurrentLiabilitiesMapper.insertNonCurrentLiabilities(nonCurrentLiabilities);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	/**
	 * 修改数据
	 * 
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities, String token)
			throws Exception {
		resultBean = new ResultBean<>();
		if (null != nonCurrentLiabilities && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);

			nonCurrentLiabilities.setUpdateTime(new Date());
			nonCurrentLiabilities.setUpdateUserId(user.getId());
			try {
				nonCurrentLiabilitiesMapper.updateNonCurrentLiabilities(nonCurrentLiabilities);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	/**
	 * 删除数据
	 * 
	 * @param nonCurrentLiabilities
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteNonCurrentLiabilities(NonCurrentLiabilities nonCurrentLiabilities, String token)
			throws Exception {
		resultBean = new ResultBean<>();
		if (null != nonCurrentLiabilities && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			nonCurrentLiabilities.setDel("-1");
			nonCurrentLiabilities.setUpdateTime(new Date());
			nonCurrentLiabilities.setUpdateUserId(user.getId());
			try {
				nonCurrentLiabilitiesMapper.updateNonCurrentLiabilities(nonCurrentLiabilities);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	/**
	 * 查询数据
	 * 
	 * @param companyStockId
	 * @return
	 */
	@Override
	public ResultBean<Object> selectNonCurrentLiabilitiesByCompanyStockId(String companyStockId) {
		resultBean = new ResultBean<>();
		if (!StringUtils.isEmpty(companyStockId)) {
			try {
				list = nonCurrentLiabilitiesMapper.selectNonCurrentLiabilitiesByCompanyStockId(companyStockId);
				resultBean.setB(true);
				resultBean.setResult(JSON.toJSONString(list));
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
