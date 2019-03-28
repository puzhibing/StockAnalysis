package com.puzhibing.StockAnalysis.controller;

import com.puzhibing.StockAnalysis.service.DataAnalysisService;
import com.puzhibing.StockAnalysis.utils.ResultBeanUtil;
import com.puzhibing.StockAnalysis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/DataAnalysis")
public class DataAnalysisController {

    @Autowired
    private DataAnalysisService dataAnalysisServiceImpl;

    private ResultBeanUtil<Object> resultBeanUtilObj;


    /**
     * 资产负债比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm 统计方式
     * @return
     */
    @RequestMapping(value = "/assetLiabilityRatio")
    public ResultBeanUtil<Object> assetLiabilityRatio(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.assetLiabilityRatio(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }


    /**
     * 流动资产/非流动资产比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm 统计方式
     * @return
     */
    @RequestMapping(value = "/cancar")
    public ResultBeanUtil<Object> cancar(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.cancar(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }


    /**
     * 流动负债/非流动负债比
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm 统计方式
     * @return
     */
    @RequestMapping(value = "/clnclr")
    public ResultBeanUtil<Object> clnclr(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.clnclr(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }





    /**
     * 经营能力分析
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm 统计方式
     * @return
     */
    @RequestMapping(value = "/managementCapacity")
    public ResultBeanUtil<Object> managementCapacity(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.managementCapacity(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }


    /**
     * 经营活动结构分析
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm
     * @return
     */
    @RequestMapping(value = "/aotsoba")
    public ResultBeanUtil<Object> aotsoba(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.aotsoba(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }


    /**
     *
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm
     * @return
     */
    @RequestMapping(value = "/comprehensiveAnalysis")
    public ResultBeanUtil<Object> comprehensiveAnalysis(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm){
        if(StringUtils.isNotEmpty(stockTypeId) && StringUtils.isNotEmpty(sm)){
            try {
                resultBeanUtilObj = dataAnalysisServiceImpl.comprehensiveAnalysis(startTime , endTime , industryId , stockTypeId , companyId, sm);
            }catch (Exception e){
                resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("逻辑处理异常" , false);
            }
        }else {
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("请求参数异常" , false);
        }
        return resultBeanUtilObj;
    }
}
