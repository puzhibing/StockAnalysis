package com.puzhibing.StockAnalysis.service.impl;

import com.puzhibing.StockAnalysis.dao.mapper.*;
import com.puzhibing.StockAnalysis.pojo.*;
import com.puzhibing.StockAnalysis.service.DataAnalysisService;
import com.puzhibing.StockAnalysis.utils.DateUtilEnum;
import com.puzhibing.StockAnalysis.utils.DateUtils;
import com.puzhibing.StockAnalysis.utils.ResultBeanUtil;
import com.puzhibing.StockAnalysis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;


@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyStockMapper companyStockMapper;

    @Autowired
    private CurrentAssetsMapper currentAssetsMapper;

    @Autowired
    private CurrentLiabilitiesMapper currentLiabilitiesMapper;

    @Autowired
    private NonCurrentAssetsMapper nonCurrentAssetsMapper;

    @Autowired
    private NonCurrentLiabilitiesMapper nonCurrentLiabilitiesMapper;

    private ResultBeanUtil<Object> resultBeanUtilObj;


    /**
     * 资产负债比
     * @param startTime     "2010-01"
     * @param endTime       "2010-01"
     * @param industryId    "行业id"
     * @param stockTypeId    ""
     * @param companyId
     * @return
     * @throws Exception
     */
    @Override
    public ResultBeanUtil<Object> assetLiabilityRatio(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);
        List<Object> li = new ArrayList<>();
        Map<String , Object> map = new HashMap<>();
        Map<String , Object> _map = new HashMap<>();
        Map<String , Object> _map_ = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = null;
            if(StringUtils.isNotEmpty(industryId)){
                companyStocks = companyStockMapper.selectCompanyStockByIndustryAndStockTypeId(industryId , stockTypeId);

            }else if(StringUtils.isNotEmpty(companyId)){
                companyStocks = new ArrayList<>();
                CompanyStock companyStock =
                    companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId , stockTypeId);
                companyStocks.add(companyStock);

            }

            List<String> dates = this.getDateByQuarter(start , end);

            List<Object> list = new ArrayList<>();
            List<Object> list2 = new ArrayList<>();
            List<Object> list3 = new ArrayList<>();
            for (CompanyStock c : companyStocks){
                Map<String , Object> map1 = new HashMap<>();
                Map<String , Object> map2 = new HashMap<>();
                Map<String , Object> map3 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name" , name + "(" + c.getStockCode() + ")");
                map2.put("name" , name + "(" + c.getStockCode() + ")");
                map3.put("name" , name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<CurrentAssets> currentAssets =
                    currentAssetsMapper.selectCurrentAssetsBycompanyStockId(c.getId() , start , end);
                List<CurrentLiabilities> currentLiabilities =
                    currentLiabilitiesMapper.selectCurrentLiabilities(c.getId() , start , end);
                List<NonCurrentAssets> nonCurrentAssets =
                    nonCurrentAssetsMapper.selectNonCurrentAssetsByCompanyStockId(c.getId() , start , end);
                List<NonCurrentLiabilities> nonCurrentLiabilities =
                    nonCurrentLiabilitiesMapper.selectNonCurrentLiabilitiesByCompanyStockId(c.getId() , start , end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                List<Double> datas2 = new ArrayList<>();
                List<Double> datas3 = new ArrayList<>();
                for (String date : dates) {
                    double c1 = 0;
                    for (CurrentAssets cu : currentAssets) {
                        if(date.equals(cu.getDataTime())){
                            c1 = Double.valueOf(cu.getTca());
                            break;
                        }
                    }

                    double c2 = 0;
                    for (CurrentLiabilities cu : currentLiabilities) {
                        if(date.equals(cu.getDataTime())){
                            c2 = Double.valueOf(cu.getTcl());
                            break;
                        }
                    }

                    double d1 = 0;
                    for (NonCurrentAssets cu : nonCurrentAssets) {
                        if(date.equals(cu.getDataTime())){
                            d1 = Double.valueOf(cu.getTnca());
                            break;
                        }
                    }

                    double d2 = 0;
                    for (NonCurrentLiabilities cu : nonCurrentLiabilities) {
                        if(date.equals(cu.getDataTime())){
                            d2 = Double.valueOf(cu.getTncl());
                            break;
                        }
                    }

                    double e1 = c1 + d1;
                    double e2 = c2 + d2;


                    if(0 == c1){
                        datas.add(c1);
                    }else{
                        BigDecimal b1 = new BigDecimal(c1);
                        BigDecimal b2 = new BigDecimal(c2);
                        double value = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(value);
                    }

                    if(0 == d1){
                        datas2.add(d1);
                    }else{
                        BigDecimal b1 = new BigDecimal(d1);
                        BigDecimal b2 = new BigDecimal(d2);
                        double value = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas2.add(value);
                    }

                    if(0 == e1){
                        datas3.add(e1);
                    }else{
                        BigDecimal b1 = new BigDecimal(e1);
                        BigDecimal b2 = new BigDecimal(e2);
                        double value = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas3.add(value);
                    }

                }
                map1.put("data" , datas);
                map2.put("data" , datas2);
                map3.put("data" , datas3);
                list.add(map1);
                list2.add(map2);
                list3.add(map3);
            }

            map.put("date" , dates);
            map.put("value" , list);
            _map.put("date" , dates);
            _map.put("value" , list2);
            _map_.put("date" , dates);
            _map_.put("value" , list3);
            li.add(map);
            li.add(_map);
            li.add(_map_);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , li);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
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
     * @return
     * @throws Exception
     */
    public ResultBeanUtil<Object> cancar(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);
        Map<String , Object> map = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = null;
            if(!StringUtils.isEmpty(industryId)){
                companyStocks = companyStockMapper.selectCompanyStockByIndustryAndStockTypeId(industryId , stockTypeId);

            }else if(!StringUtils.isEmpty(companyId)){
                companyStocks = new ArrayList<>();
                CompanyStock companyStock =
                        companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId , stockTypeId);
                companyStocks.add(companyStock);

            }

            List<String> dates = this.getDateByQuarter(start , end);

            List<Object> list = new ArrayList<>();
            for (CompanyStock c : companyStocks) {
                Map<String, Object> map1 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name", name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<CurrentAssets> currentAssets =
                        currentAssetsMapper.selectCurrentAssetsBycompanyStockId(c.getId(), start, end);
                List<NonCurrentAssets> nonCurrentAssets =
                        nonCurrentAssetsMapper.selectNonCurrentAssetsByCompanyStockId(c.getId(), start, end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                for (String date : dates) {
                    double c1 = 0;
                    for (CurrentAssets cu : currentAssets) {
                        if (date.equals(cu.getDataTime())) {
                            c1 = Double.valueOf(cu.getTca());
                            break;
                        }
                    }

                    double d1 = 0;
                    for (NonCurrentAssets cu : nonCurrentAssets) {
                        if (date.equals(cu.getDataTime())) {
                            d1 = Double.valueOf(cu.getTnca());
                            break;
                        }
                    }

                    if (0 == c1) {
                        datas.add(c1);
                    } else {
                        BigDecimal b1 = new BigDecimal(c1);
                        BigDecimal b2 = new BigDecimal(d1);
                        double value = b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(value);
                    }
                }
                map1.put("data", datas);
                list.add(map1);
            }
            map.put("date" , dates);
            map.put("value" , list);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , map);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
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
     * @return
     * @throws Exception
     */
    public ResultBeanUtil<Object> clnclr(String startTime, String endTime, String industryId, String stockTypeId, String companyId) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);
        Map<String , Object> map = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = null;
            if(!StringUtils.isEmpty(industryId)){
                companyStocks = companyStockMapper.selectCompanyStockByIndustryAndStockTypeId(industryId , stockTypeId);

            }else if(!StringUtils.isEmpty(companyId)){
                companyStocks = new ArrayList<>();
                CompanyStock companyStock =
                        companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId , stockTypeId);
                companyStocks.add(companyStock);

            }

            List<String> dates = this.getDateByQuarter(start , end);

            List<Object> list = new ArrayList<>();
            for (CompanyStock c : companyStocks) {
                Map<String, Object> map1 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name", name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<CurrentLiabilities> currentLiabilities =
                        currentLiabilitiesMapper.selectCurrentLiabilities(c.getId() , start , end);
                List<NonCurrentLiabilities> nonCurrentLiabilities =
                        nonCurrentLiabilitiesMapper.selectNonCurrentLiabilitiesByCompanyStockId(c.getId() , start , end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                for (String date : dates) {
                    double c2 = 0;
                    for (CurrentLiabilities cu : currentLiabilities) {
                        if(date.equals(cu.getDataTime())){
                            c2 = Double.valueOf(cu.getTcl());
                            break;
                        }
                    }

                    double d2 = 0;
                    for (NonCurrentLiabilities cu : nonCurrentLiabilities) {
                        if(date.equals(cu.getDataTime())){
                            d2 = Double.valueOf(cu.getTncl());
                            break;
                        }
                    }

                    if (0 == c2) {
                        datas.add(c2);
                    } else {
                        BigDecimal b1 = new BigDecimal(c2);
                        BigDecimal b2 = new BigDecimal(d2);
                        double value = b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(value);
                    }
                }
                map1.put("data", datas);
                list.add(map1);
            }
            map.put("date" , dates);
            map.put("value" , list);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , map);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return resultBeanUtilObj;
    }





    /**
     * 获取给定日期范围，按照季度划分后的字符串集合
     * @param start
     * @param end
     * @return
     */
    private List<String> getDateByQuarter(Date start , Date end){
        //构建两个日历对象用于处理时间
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);

        /**
         * 开始处理时间
         */
        List<String> dates = new ArrayList<>();
        int sm = DateUtils.getRecentQuarter(start);
        long l1 = calendar1.getTimeInMillis();
        long l2 = calendar2.getTimeInMillis();
        if(l1 <= l2){
            calendar1.set(Calendar.MONTH , sm);
            calendar1.set(Calendar.DATE , 0);
            dates.add(DateUtils.getDateToString(calendar1.getTime() , DateUtilEnum.SHORTBAR));
        }else {
            return dates;
        }

        boolean b = true;
        while (b){
            l1 = calendar1.getTimeInMillis();
            l2 = calendar2.getTimeInMillis();
            sm = calendar1.get(Calendar.MONTH) + 1;//新月份
            if(l1 <= l2){
                calendar1.set(Calendar.MONTH , sm + 3);
                calendar1.set(Calendar.DATE , 0);
                dates.add(DateUtils.getDateToString(calendar1.getTime() , DateUtilEnum.SHORTBAR));
            }else {
                b = false;
            }

        }
        return dates;
    }

}



