package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.puzhibing.StockAnalysis.dao.mapper.StockExchangeMapper;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.StockExchange;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.StockExchangeService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;

/**
 * 证券交易所
 * @author asus
 *
 */
@Service
public class StockExchangeServiceImpl implements StockExchangeService {

	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private StockExchangeMapper stockExchangeMapper;
	
	
	
	/**
	 * 添加数据
	 * @param stockExchange
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultBean<Object> insertStockExchange(StockExchange stockExchange, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != stockExchange && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			stockExchange.setId(uuidutil.getUUID());
			stockExchange.setDel("0");
			stockExchange.setInsertTime(new Date());
			stockExchange.setInsertUserId(user.getId());
			try {
				stockExchangeMapper.insertStockExchange(stockExchange);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	
	
	/**
	 * 修改数据
	 * @param stockExchange
	 * @param token
	 * @return
	 */
	@Override
	public ResultBean<Object> updateStockExchange(StockExchange stockExchange, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(null != stockExchange && !StringUtils.isEmpty(token)) {
			User user = tokenutil.tokenToUser(token);
			stockExchange.setUpdateTime(new Date());
			stockExchange.setUpdateUserId(user.getId());
			try {
				stockExchangeMapper.updateStockExchange(stockExchange);
				resultBean.setB(true);
			} catch (Exception e) {
				throw e;
			}
		}
		return resultBean;
	}



	
	/**
	 * 删除数据
	 * @param id
	 * @param token
	 * @return
	 */
	@Override
	public ResultBean<Object> deleteStockExchange(String id, String token) throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		if(!(StringUtils.isEmpty(token) && StringUtils.isEmpty(id))) {
			User user = tokenutil.tokenToUser(token);
			
			try {
				StockExchange stockExchange = stockExchangeMapper.selectStockExchangeById(id);
				stockExchange.setDel("-1");
				stockExchange.setUpdateTime(new Date());
				stockExchange.setUpdateUserId(user.getId());
				
				stockExchangeMapper.deleteStockExchange(stockExchange);
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
	 */
	@Override
	public ResultBean<Object> selectAllStockExchange() throws Exception {
		ResultBean<Object> resultBean = new ResultBean<>();
		try {
			List<StockExchange> list = stockExchangeMapper.selectAllStockExchange();
			String json = JSON.toJSONString(list);
			resultBean.setB(true);
			resultBean.setResult(json);
		} catch (Exception e) {
			throw e;
		}
		return resultBean;
	}

}
