package com.puzhibing.StockAnalysis.service.impl;

import com.puzhibing.StockAnalysis.dao.mapper.IndustryMapper;
import com.puzhibing.StockAnalysis.pojo.ResultBean;
import com.puzhibing.StockAnalysis.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public ResultBean<Object> stdtar(String startTime, String endTime, String IndustryId, String value) throws Exception {

        return null;
    }
}
