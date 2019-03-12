package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.pojo.ResultBean;

public interface DataAnalysisService {

    /**
     * 短期负债资产比
     * @param startTime
     * @param endTime
     * @param IndustryId
     * @param value
     * @return
     * @throws Exception
     */
    ResultBean<Object> stdtar(String startTime , String endTime , String IndustryId , String value) throws Exception;
}
