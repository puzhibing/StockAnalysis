package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.StockTypeMapper;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockType;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.StockTypeService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;


@Service
public class StockTypeServiceImpl implements StockTypeService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private StockTypeMapper stockTypeMapper;

	
	
	/**
	 * 添加数据
	 * @param stockType
	 * @param token
	 * @return
	 */
	@Override
	public ResultBean<Object> insertStockType(StockType stockType, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != stockType && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			stockType.setId(uuidutil.getUUID());
			stockType.setDel("0");
			stockType.setInsertTime(new Date());
			stockType.setInsertUserId(user.getId());
			try {
				stockTypeMapper.insertStockType(stockType);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	/**
	 * 修改数据
	 * @param stockType
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateStockType(StockType stockType, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != stockType && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			stockType.setUpdateTime(new Date());
			stockType.setUpdateUserId(user.getId());
			try {
				stockTypeMapper.updateStockType(stockType);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	
	/**
	 * 删除数据
	 * @param stockType
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteStockType(StockType stockType, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != stockType && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			stockType.setDel("-1");
			stockType.setUpdateTime(new Date());
			stockType.setUpdateUserId(user.getId());
			try {
				stockTypeMapper.updateStockType(stockType);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> selectAllStockType() throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		try {
			List<StockType> list = stockTypeMapper.selectAllStockType();
			String json = JSON.toJSONString(list);
			resultBean.setB(true);
			resultBean.setResult(json);
		} catch (Exception e) {
			throw e;
		}
		return resultBean;
	}

}
