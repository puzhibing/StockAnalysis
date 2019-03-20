package com.puzhibing.StockAnalysis.service;

import com.puzhibing.StockAnalysis.utils.ResultBeanUtil;

public interface DataAnalysisService {

    /**
     * 资产/负债比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @return
     * @throws Exception
     */
    ResultBeanUtil<Object> assetLiabilityRatio(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception;


    /**
     * 流动资产/非流动资产比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @return
     * @throws Exception
     */
    ResultBeanUtil<Object> cancar(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception;


    /**
     * 流动负债/非流动负债比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @return
     * @throws Exception
     */
    ResultBeanUtil<Object> clnclr(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception;


    /**
     * 经营能力分析
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @return
     * @throws Exception
     */
    ResultBeanUtil<Object> managementCapacity(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception;
}
