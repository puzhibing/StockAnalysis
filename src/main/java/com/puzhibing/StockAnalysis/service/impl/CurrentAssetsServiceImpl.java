package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import com.puzhibing.StockAnalysis.utils.UnitCalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.CurrentAssetsMapper;
import com.puzhibing.StockAnalysis.pojo.CurrentAssets;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.CurrentAssetsService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;

/**
 * 流动资产Service
 */
@Service
public class CurrentAssetsServiceImpl implements CurrentAssetsService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private CurrentAssetsMapper currentAssetsMapper;

	@Autowired
	private UnitCalculationUtil unitCalculationUtil;
	
	private User user;
	
	private ResultBean<Object> resultBean;
	
	private List<CurrentAssets> list;
	
	

	/**
	 * 添加数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertCurrentAssets(CurrentAssets currentAssets , String currencyUnit , String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentAssets && !(StringUtils.isEmpty(token) && StringUtils.isEmpty(currencyUnit))) {
			currentAssets = (CurrentAssets)unitCalculationUtil.calculation(currencyUnit , currentAssets , CurrentAssets.class);

			user = tokenutil.tokenToUser(token);
			currentAssets.setId(uuidutil.getUUID());
			currentAssets.setDel("0");
			currentAssets.setInsertTime(new Date());
			currentAssets.setInsertUserId(user.getId());
			try {
				currentAssetsMapper.insertCurrentAssets(currentAssets);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	/**
	 * 修改数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateCurrentAssets(CurrentAssets currentAssets , String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentAssets && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			currentAssets.setUpdateTime(new Date());
			currentAssets.setUpdateUserId(user.getId());
			try {
				currentAssetsMapper.updateCurrentAssets(currentAssets);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

	
	
	
	
	/**
	 * 删除数据
	 * @param currentAssets
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteCurrentAssets(CurrentAssets currentAssets , String token) throws Exception {
		resultBean = new ResultBean<>();
		if(null != currentAssets && !StringUtils.isEmpty(token)) {
			user = tokenutil.tokenToUser(token);
			currentAssets.setUpdateTime(new Date());
			currentAssets.setUpdateUserId(user.getId());
			try {
				currentAssetsMapper.deleteCurrentAssets(currentAssets);
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
	public ResultBean<Object> selectCurrentAssetsBycompanyStockId(String companyStockId) throws Exception {
		resultBean = new ResultBean<>();
		if(!StringUtils.isEmpty(companyStockId)) {
			try {
				list = currentAssetsMapper.selectCurrentAssetsBycompanyStockId(companyStockId);
				resultBean.setResult(JSON.toJSONString(list));
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}

}
