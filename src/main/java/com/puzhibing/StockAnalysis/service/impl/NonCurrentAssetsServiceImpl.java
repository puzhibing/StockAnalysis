package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.NonCurrentAssetsMapper;
import com.puzhibing.StockAnalysis.pojo.NonCurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.NonCurrentAssetsService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;
import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;



@Service
public class NonCurrentAssetsServiceImpl implements NonCurrentAssetsService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private NonCurrentAssetsMapper nonCurrentAssetsMapper;
	
	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	private ResultBean<Object> resultBean;
	
	private User user;
	
	private List<NonCurrentAssets> list;
	
	

	
	/**
	 * 添加数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 */
	@Override
	public ResultBean<Object> insertNonCurrentAssets(NonCurrentAssets nonCurrentAssets , String currencyUnit , String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != nonCurrentAssets && !(StringUtils.isEmpty(token) && StringUtils.isEmpty(currencyUnit))) {
			nonCurrentAssets = (NonCurrentAssets)unitCalculationUtil.calculation(currencyUnit , nonCurrentAssets , NonCurrentAssets.class);

//			user = tokenutil.tokenToUser(token);
			nonCurrentAssets.setId(uuidutil.getUUID());
			nonCurrentAssets.setDel("0");
			nonCurrentAssets.setInsertTime(new Date());
//			nonCurrentAssets.setInsertUserId(user.getId());
			try {
				nonCurrentAssetsMapper.insertNonCurrentAssets(nonCurrentAssets);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	/**
	 * 修改数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateNonCurrentAssets(NonCurrentAssets nonCurrentAssets, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != nonCurrentAssets && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			nonCurrentAssets.setUpdateTime(new Date());
			nonCurrentAssets.setUpdateUserId(user.getId());
			try {
				nonCurrentAssetsMapper.updateNonCurrentAssets(nonCurrentAssets);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	/**
	 * 删除数据
	 * @param nonCurrentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteNonCurrentAssets(NonCurrentAssets nonCurrentAssets, String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != nonCurrentAssets && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			nonCurrentAssets.setUpdateTime(new Date());
			nonCurrentAssets.setUpdateUserId(user.getId());
			try {
				nonCurrentAssetsMapper.deleteNonCurrentAssets(nonCurrentAssets);
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
	public ResultBean<Object> selectNonCurrentAssetsByCompanyStockId(String companyStockId) throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				list = nonCurrentAssetsMapper.selectNonCurrentAssetsByCompanyStockId(companyStockId);
				resultBean.setB(true);
				resultBean.setResult(JSON.toJSONString(list));
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
