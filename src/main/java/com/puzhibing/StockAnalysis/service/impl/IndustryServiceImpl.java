package com.puzhibing.StockAnalysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puzhibing.StockAnalysis.dao.mapper.IndustryMapper;
import com.puzhibing.StockAnalysis.pojo.Industry;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.pojo.User;
import com.puzhibing.StockAnalysis.service.IndustryService;
import com.puzhibing.StockAnalysis.utils.TokenUtil;
import com.puzhibing.StockAnalysis.utils.UUIDUtil;

/**
 * 行业
 * @author Administrator
 *
 */
@Service
public class IndustryServiceImpl implements IndustryService {
	
	@Autowired
	private UUIDUtil uuidutil;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private IndustryMapper industryMapper;
	
	private User user;
	
	private ResultBean<Object> resultUtil;

	
	/**
	 * 添加数据
	 * @param industry
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> insertIndustry(Industry industry, String token) throws Exception {
		resultUtil = new ResultBean<Object>();
		user = tokenutil.tokenToUser(token);
		industry.setId(uuidutil.getUUID());
		industry.setDel("0");
		industry.setInsertTime(new Date());
		industry.setInsertUserId(user.getId());
		try {
			industryMapper.insertIndustry(industry);
			resultUtil.setB(true);
		} catch (Exception e) {
			throw e;
		}
		return resultUtil;
	}


	
	
	/**
	 * 修改数据
	 * @param industry
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> updateIndustry(Industry industry, String token) throws Exception {
		resultUtil = new ResultBean<Object>();
		user = tokenutil.tokenToUser(token);
		industry.setDel("0");
		industry.setUpdateTime(new Date());
		industry.setUpdateUserId(user.getId());
		try {
			industryMapper.updateIndustry(industry);
			resultUtil.setB(true);
		} catch (Exception e) {
			throw e;
		}
		return resultUtil;
	}


	
	
	/**
	 * 删除数据
	 * @param industry
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<Object> deleteIndustry(Industry industry, String token) throws Exception {
		resultUtil = new ResultBean<Object>();
		user = tokenutil.tokenToUser(token);
		industry.setDel("-1");
		industry.setUpdateTime(new Date());
		industry.setUpdateUserId(user.getId());
		try {
			industryMapper.deleteIndustry(industry);
			resultUtil.setB(true);
		} catch (Exception e) {
			throw e;
		}
		return resultUtil;
	}


	
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBean<List<Industry>> selectAllIndustry() throws Exception {
		ResultBean<List<Industry>> resultBean = new ResultBean<>();
		try {
			List<Industry> list = industryMapper.selectAllIndustry();
			resultBean.setB(true);
			resultBean.setResult(list);
		} catch (Exception e) {
			throw e;
		}
		return resultBean;
	}

}
