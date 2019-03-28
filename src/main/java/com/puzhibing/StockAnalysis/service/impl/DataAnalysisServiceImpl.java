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

    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private CashFlowMapper cashFlowMapper;

    private ResultBeanUtil<Object> resultBeanUtilObj;


    /**
     * 偿债能力分析
     * @param startTime     "2010-01"
     * @param endTime       "2010-01"
     * @param industryId    "行业id"
     * @param stockTypeId    ""
     * @param companyId
     * @param sm 统计方式
     * @return
     * @throws Exception
     */
    @Override
    public ResultBeanUtil<Object> assetLiabilityRatio(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);

        List<Object> li = new ArrayList<>();

        Map<String , Object> crMap = new HashMap<>();
        Map<String , Object> crsmMap = new HashMap<>();
        Map<String , Object> qrMap = new HashMap<>();
        Map<String , Object> qrsmMap = new HashMap<>();
        Map<String , Object> aalMap = new HashMap<>();
        Map<String , Object> aalsmMap = new HashMap<>();

        try {
            List<CompanyStock> companyStocks = this.getCompanyStock(stockTypeId, industryId, companyId);
            List<String> dates = this.getDates(sm, start, end);

            crMap.put("date", dates);
            crsmMap.put("date", dates);
            qrMap.put("date", dates);
            qrsmMap.put("date", dates);

            List crList = new ArrayList<>();
            List crsmList = new ArrayList<>();
            List qrList = new ArrayList<>();
            List qrsmList = new ArrayList<>();
            List aalList = new ArrayList<>();
            List aalsmList = new ArrayList<>();
            for (CompanyStock c : companyStocks){

                Map<String, Object> crMapInfo = new HashMap<>();
                Map<String, Object> crsmMapInfo = new HashMap<>();
                Map<String, Object> qrMapInfo = new HashMap<>();
                Map<String, Object> qrsmMapInfo = new HashMap<>();
                Map<String, Object> aalMapInfo = new HashMap<>();
                Map<String, Object> aalsmMapInfo = new HashMap<>();

                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }

                //获取基础数据
                List<CurrentAssets> currentAssets =
                    currentAssetsMapper.selectCurrentAssetsBycompanyStockId(c.getId() , start , end);
                List<CurrentLiabilities> currentLiabilities =
                    currentLiabilitiesMapper.selectCurrentLiabilities(c.getId() , start , end);
                List<NonCurrentAssets> nonCurrentAssets =
                    nonCurrentAssetsMapper.selectNonCurrentAssetsByCompanyStockId(c.getId() , start , end);
                List<NonCurrentLiabilities> nonCurrentLiabilities =
                    nonCurrentLiabilitiesMapper.selectNonCurrentLiabilitiesByCompanyStockId(c.getId() , start , end);


                List<Object> currentRatio = this.getCurrentRatio(dates, currentAssets, currentLiabilities);
                crMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                crMapInfo.put("data" , currentRatio.get(0));
                crList.add(crMapInfo);
                crsmMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                crsmMapInfo.put("data" , currentRatio.get(1));
                crsmList.add(crsmMapInfo);

                List<Object> quickRatio = this.getQuickRatio(dates, currentAssets, currentLiabilities);
                qrMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                qrMapInfo.put("data" , quickRatio.get(0));
                qrList.add(qrMapInfo);
                qrsmMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                qrsmMapInfo.put("data" , quickRatio.get(1));
                qrsmList.add(qrsmMapInfo);

                List<Object> assetsAndLiabilities = this.getAssetsAndLiabilities(dates, currentAssets, currentLiabilities,
                        nonCurrentAssets, nonCurrentLiabilities);
                aalMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                aalMapInfo.put("data" , assetsAndLiabilities.get(0));
                aalList.add(aalMapInfo);
                aalsmMapInfo.put("name" , name + "(" + c.getStockCode() + ")");
                aalsmMapInfo.put("data" , assetsAndLiabilities.get(1));
                aalsmList.add(aalsmMapInfo);

            }
            crMap.put("value", crList);
            li.add(crMap);
            crsmMap.put("value", crsmList);
            li.add(crsmMap);

            qrMap.put("value", qrList);
            li.add(qrMap);
            qrsmMap.put("value", qrsmList);
            li.add(qrsmMap);

            aalMap.put("value", aalList);
            li.add(aalMap);
            aalsmMap.put("value", aalsmList);
            li.add(aalsmMap);

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
     * @param sm 统计方式
     * @return
     * @throws Exception
     */
    public ResultBeanUtil<Object> cancar(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);

        List<Object> ls = new ArrayList<>();
        Map<String , Object> map = new HashMap<>();
        Map<String , Object> _map = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = this.getCompanyStock(stockTypeId, industryId, companyId);
            List<String> dates = this.getDates(sm, start, end);

            List<Object> list = new ArrayList<>();
            List<Object> list1 = new ArrayList<>();
            for (CompanyStock c : companyStocks) {
                Map<String, Object> map1 = new HashMap<>();
                Map<String, Object> map2 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name", name + "(" + c.getStockCode() + ")");
                map2.put("name", name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<CurrentAssets> currentAssets =
                        currentAssetsMapper.selectCurrentAssetsBycompanyStockId(c.getId(), start, end);
                List<NonCurrentAssets> nonCurrentAssets =
                        nonCurrentAssetsMapper.selectNonCurrentAssetsByCompanyStockId(c.getId(), start, end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                List<Double> os = new ArrayList<>();
                double o = 0;
                for (int i = 0 ; i < dates.size() ; i++) {
                    double c1 = 0;
                    for (CurrentAssets cu : currentAssets) {
                        if (dates.get(i).equals(cu.getDataTime())) {
                            c1 = Double.valueOf(cu.getTca());
                            break;
                        }
                    }

                    double d1 = 0;
                    for (NonCurrentAssets cu : nonCurrentAssets) {
                        if (dates.get(i).equals(cu.getDataTime())) {
                            d1 = Double.valueOf(cu.getTnca());
                            break;
                        }
                    }

                    double val = 0;
                    if (0 == c1) {
                        datas.add(c1);
                    } else {
                        BigDecimal b1 = new BigDecimal(c1);
                        BigDecimal b2 = new BigDecimal(d1);
                        val = b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(val);
                    }

                    //计算增长率
                    if(0 == val){
                        os.add(new Double(0));
                    }else{
                        if(0 == o){
                            os.add(new Double(1));
                        }else {
                            BigDecimal b1 = new BigDecimal(val - o);
                            BigDecimal b2 = new BigDecimal(o);
                            os.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                        }

                    }
                    o = val;
                }
                map1.put("data", datas);
                map2.put("data", os);
                list.add(map1);
                list1.add(map2);
            }
            map.put("date" , dates);
            _map.put("date" , dates);
            map.put("value" , list);
            _map.put("value" , list1);

            ls.add(map);
            ls.add(_map);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , ls);
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
     * @param sm 统计方式
     * @return
     * @throws Exception
     */
    public ResultBeanUtil<Object> clnclr(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);

        List<Object> li = new ArrayList<>();
        Map<String , Object> map = new HashMap<>();
        Map<String , Object> _map = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = this.getCompanyStock(stockTypeId, industryId, companyId);
            List<String> dates = this.getDates(sm, start, end);

            List<Object> list = new ArrayList<>();
            List<Object> list1 = new ArrayList<>();
            for (CompanyStock c : companyStocks) {
                Map<String, Object> map1 = new HashMap<>();
                Map<String, Object> map2 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name", name + "(" + c.getStockCode() + ")");
                map2.put("name", name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<CurrentLiabilities> currentLiabilities =
                        currentLiabilitiesMapper.selectCurrentLiabilities(c.getId() , start , end);
                List<NonCurrentLiabilities> nonCurrentLiabilities =
                        nonCurrentLiabilitiesMapper.selectNonCurrentLiabilitiesByCompanyStockId(c.getId() , start , end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                List<Double> os = new ArrayList<>();
                double o = 0;
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

                    double val = 0;
                    if (0 == c2) {
                        datas.add(c2);
                    } else {
                        BigDecimal b1 = new BigDecimal(c2);
                        BigDecimal b2 = new BigDecimal(d2);
                        val = b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(val);
                    }

                    //计算增长率
                    if(0 == val){
                        os.add(new Double(0));
                    }else{
                        if(0 == o){
                            os.add(new Double(1));
                        }else {
                            BigDecimal b1 = new BigDecimal(val - o);
                            BigDecimal b2 = new BigDecimal(o);
                            os.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                        }

                    }
                    o = val;
                }
                map1.put("data", datas);
                map2.put("data", os);
                list.add(map1);
                list1.add(map2);
            }
            map.put("date" , dates);
            map.put("value" , list);
            _map.put("date" , dates);
            _map.put("value" , list1);

            li.add(map);
            li.add(_map);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , li);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return resultBeanUtilObj;
    }


    /**
     * 营收比例（营业收入/营业支出）
     * @param startTime
     * @param endTime
     * @param industryId
     * @param stockTypeId
     * @param companyId
     * @param sm 统计方式
     * @return
     * @throws Exception
     */
    @Override
    public ResultBeanUtil<Object> managementCapacity(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);

        List<Object> li = new ArrayList<>();
        Map<String , Object> map = new HashMap<>();
        Map<String , Object> _map = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = this.getCompanyStock(stockTypeId, industryId, companyId);
            List<String> dates = this.getDates(sm, start, end);

            List<Object> list = new ArrayList<>();
            List<Object> list1 = new ArrayList<>();
            for (CompanyStock c : companyStocks){
                Map<String , Object> map1 = new HashMap<>();
                Map<String , Object> map2 = new HashMap<>();
                Company company = companyMapper.selectCompanyById(c.getCompanyId());
                String name = "";
                if(StringUtils.isNotEmpty(company.getChShortName())){
                    name = company.getChShortName();
                }else{
                    name = company.getChName();
                }
                map1.put("name" , name + "(" + c.getStockCode() + ")");
                map2.put("name" , name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<Profit> profits =
                    profitMapper.selectProfitByCompanyStockId(c.getId() , start , end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                List<Double> os = new ArrayList<>();
                double o = 0;
                for (String date : dates) {
                    double c1 = 0;
                    for (Profit cu : profits) {
                        if(date.equals(cu.getDataTime())){
                            c1 = Double.valueOf(cu.getToi());
                            break;
                        }
                    }

                    double c2 = 0;
                    for (Profit cu : profits) {
                        if(date.equals(cu.getDataTime())){
                            c2 = Double.valueOf(cu.getToc()) * -1;
                            break;
                        }
                    }

                    double val = 0;
                    if(0 == c1){
                        datas.add(c1);
                    }else{
                        BigDecimal b1 = new BigDecimal(c1);
                        BigDecimal b2 = new BigDecimal(c2);
                        val = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(val);
                    }

                    //计算增长率
                    if(0 == val){
                        os.add(new Double(0));
                    }else{
                        if(0 == o){
                            os.add(new Double(1));
                        }else {
                            BigDecimal b1 = new BigDecimal(val - o);
                            BigDecimal b2 = new BigDecimal(o);
                            os.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                        }

                    }
                    o = val;
                }
                map1.put("data" , datas);
                map2.put("data" , os);
                list.add(map1);
                list1.add(map2);
            }

            map.put("date" , dates);
            map.put("value" , list);
            _map.put("date" , dates);
            _map.put("value" , list1);

            li.add(map);
            li.add(_map);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , li);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
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
     * @throws Exception
     */
    @Override
    public ResultBeanUtil<Object> aotsoba(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Date start =  DateUtils.getMonth(startTime , DateUtilEnum.SHORTBAR);
        Date end = DateUtils.getMonth(endTime , DateUtilEnum.SHORTBAR);

        List<Object> li = new ArrayList<>();
        Map<String , Object> map = new HashMap<>();
        Map<String , Object> _map = new HashMap<>();
        Map<String , Object> mapCashFlow = new HashMap<>();
        Map<String , Object> mapCashFlowSM = new HashMap<>();
        try {
            List<CompanyStock> companyStocks = this.getCompanyStock(stockTypeId, industryId, companyId);
            List<String> dates = this.getDates(sm, start, end);

            List<Object> list = new ArrayList<>();
            List<Object> list1 = new ArrayList<>();
            List<Object> list2 = new ArrayList<>();
            List<Object> list3 = new ArrayList<>();
            for (CompanyStock c : companyStocks){
                Map<String , Object> map1 = new HashMap<>();
                Map<String , Object> map2 = new HashMap<>();
                Map<String , Object> map3 = new HashMap<>();
                Map<String , Object> map4 = new HashMap<>();
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
                map4.put("name" , name + "(" + c.getStockCode() + ")");

                //获取基础数据
                List<Profit> profits =
                        profitMapper.selectProfitByCompanyStockId(c.getId() , start , end);
                List<CashFlow> cashFlows =
                        cashFlowMapper.selectCashFlowByCompanyStockId(c.getId() , start , end);

                //解析日期，获取对应日期的数据封装进集合中
                List<Double> datas = new ArrayList<>();
                List<Double> os = new ArrayList<>();
                List<Double> datas1 = new ArrayList<>();
                List<Double> os1 = new ArrayList<>();
                double o = 0;
                double o1 = 0;
                for (String date : dates) {
                    double c1 = 0;
                    for (Profit cu : profits) {
                        if(date.equals(cu.getDataTime())){
                            c1 = Double.valueOf(cu.getToi()) + Double.valueOf(cu.getToc());
                            break;
                        }
                    }

                    double c2 = 0;
                    for (Profit cu : profits) {
                        if(date.equals(cu.getDataTime())){
                            c2 = Double.valueOf(cu.getTci());
                            break;
                        }
                    }

                    double val = 0;
                    if(0 == c1){
                        datas.add(c1);
                    }else{
                        BigDecimal b1 = new BigDecimal(c1);
                        BigDecimal b2 = new BigDecimal(c2);
                        val = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas.add(val);
                    }

                    //计算增长率
                    if(0 == val){
                        os.add(new Double(0));
                    }else{
                        if(0 == o){
                            os.add(new Double(1));
                        }else {
                            BigDecimal b1 = new BigDecimal(val - o);
                            BigDecimal b2 = new BigDecimal(o);
                            os.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                        }

                    }
                    o = val;


                    double d1 = 0;
                    for(CashFlow cf : cashFlows){
                        if(date.equals(cf.getDataTime())){
                            d1 = Double.valueOf(cf.getNcffoa());
                            break;
                        }
                    }
                    double d2 = 0;
                    for(CashFlow cf : cashFlows){
                        if(date.equals(cf.getDataTime())){
                            d2 = Double.valueOf(cf.getBocaceaeot()) - Double.valueOf(cf.getCaceatboty());
                            break;
                        }
                    }

                    double val1 = 0;
                    if(0 == d1){
                        datas1.add(new Double(0));
                    }else{
                        BigDecimal b1 = new BigDecimal(d1);
                        BigDecimal b2 = new BigDecimal(d2);
                        val1 = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                        datas1.add(val1);
                    }

                    if(0 == val1){
                        os1.add(new Double(0));
                    }else{
                        if(0 == o1){
                            os1.add(new Double(1));
                        }else {
                            BigDecimal b1 = new BigDecimal(val1 - o1);
                            BigDecimal b2 = new BigDecimal(o1);
                            os1.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                        }
                    }
                    o1 = val1;

                }
                map1.put("data" , datas);
                map2.put("data" , os);
                list.add(map1);
                list1.add(map2);

                map3.put("data" , datas1);
                map4.put("data" , os1);
                list2.add(map3);
                list3.add(map4);
            }

            map.put("date" , dates);
            map.put("value" , list);
            _map.put("date" , dates);
            _map.put("value" , list1);
            mapCashFlow.put("date" , dates);
            mapCashFlow.put("value" , list2);
            mapCashFlowSM.put("date" , dates);
            mapCashFlowSM.put("value" , list3);

            li.add(map);
            li.add(_map);
            li.add(mapCashFlow);
            li.add(mapCashFlowSM);
            resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功" , true , li);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
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
     * @throws Exception
     */
    @Override
    public ResultBeanUtil<Object> comprehensiveAnalysis(String startTime, String endTime, String industryId, String stockTypeId, String companyId, String sm) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ResultBeanUtil<Object> resultBeanUtil = this.aotsoba(startTime, endTime, industryId, stockTypeId, companyId, sm);
        if(resultBeanUtil.getB()){
            List<Object> list = (List<Object>) resultBeanUtil.getResult();
            map.put("directRevenueShare", list.get(0));
            map.put("directRevenueShareSM", list.get(1));
            map.put("mainBusinessCashFlowRatio", list.get(2));
            map.put("mainBusinessCashFlowRatioSM", list.get(3));
        }

        ResultBeanUtil<Object> resultBeanUtil1 = this.assetLiabilityRatio(startTime, endTime, industryId, stockTypeId, companyId, sm);
        if(resultBeanUtil1.getB()){
            List<Object> list = (List<Object>) resultBeanUtil1.getResult();
            map.put("currentRatio", list.get(0));
            map.put("currentRatioSM", list.get(1));
            map.put("quickRatio", list.get(2));
            map.put("quickRatioSM", list.get(3));
            map.put("assetsAndLiabilities", list.get(4));
            map.put("assetsAndLiabilitiesSM", list.get(5));
        }

        resultBeanUtilObj = ResultBeanUtil.getResultBeanUtil("查询成功", true, map);
        return resultBeanUtilObj;
    }


























    /**
     * 封装流动比率及增长率数据（流动资产/流动负债）
     * @param dates
     * @param currentAssets
     * @param currentLiabilities
     * @return
     */
    private List<Object> getCurrentRatio(List<String> dates , List<CurrentAssets> currentAssets ,
                                         List<CurrentLiabilities> currentLiabilities){
        List<Object> datas = new ArrayList<>();
        List<Double> data = new ArrayList();
        List<Double> sm = new ArrayList<>();
        double crsm = 0;
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
            double crVal = 0;
            //计算基础数据
            if(0 == c1){
                data.add(c1);
            }else{
                BigDecimal b1 = new BigDecimal(c1);
                BigDecimal b2 = new BigDecimal(c2);
                crVal = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                data.add(crVal);
            }

            //计算增加率部分
            if(0 == crVal){
                sm.add(new Double(0));
            }else{
                if(0 == crsm){
                    sm.add(new Double(1));
                }else {
                    BigDecimal b1 = new BigDecimal(crVal - crsm);
                    BigDecimal b2 = new BigDecimal(crsm);
                    sm.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                }

            }
            crsm = crVal;
        }
        datas.add(data);
        datas.add(sm);
        return datas;
    }


    /**
     * 封装速动比率及增长率数据（流动资产合计 - 存货 - 应收账款 - 预付账款） / 流动负债
     * @param dates
     * @param currentAssets
     * @param currentLiabilities
     * @return
     */
    private List<Object> getQuickRatio(List<String> dates , List<CurrentAssets> currentAssets ,
                                       List<CurrentLiabilities> currentLiabilities){
        List<Object> datas = new ArrayList<>();
        List<Double> data = new ArrayList();
        List<Double> sm = new ArrayList<>();
        double crsm = 0;
        for (String date : dates) {
            double c1 = 0;
            for (CurrentAssets cu : currentAssets) {
                if(date.equals(cu.getDataTime())){
                    c1 = Double.valueOf(cu.getTca()) - Double.valueOf(cu.getStock())
                            - Double.valueOf(cu.getPrepayments()) - Double.valueOf(cu.getAccountsReceivable());
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
            double crVal = 0;
            //计算基础数据
            if(0 == c1){
                data.add(c1);
            }else{
                BigDecimal b1 = new BigDecimal(c1);
                BigDecimal b2 = new BigDecimal(c2);
                crVal = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                data.add(crVal);
            }

            //计算增加率部分
            if(0 == crVal){
                sm.add(new Double(0));
            }else{
                if(0 == crsm){
                    sm.add(new Double(1));
                }else {
                    BigDecimal b1 = new BigDecimal(crVal - crsm);
                    BigDecimal b2 = new BigDecimal(crsm);
                    sm.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                }

            }
            crsm = crVal;
        }
        datas.add(data);
        datas.add(sm);
        return datas;
    }


    /**
     * 封装资产负债率及增长率数据【资产总额 / 负债总额】
     * @param dates
     * @param currentAssets
     * @param currentLiabilities
     * @param nonCurrentAssets
     * @param nonCurrentLiabilities
     * @return
     */
    private List<Object> getAssetsAndLiabilities(List<String> dates, List<CurrentAssets> currentAssets, List<CurrentLiabilities> currentLiabilities
            , List<NonCurrentAssets> nonCurrentAssets, List<NonCurrentLiabilities> nonCurrentLiabilities){
        List<Object> datas = new ArrayList<>();
        List<Double> data = new ArrayList();
        List<Double> sm = new ArrayList<>();
        double crsm = 0;
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
            for (NonCurrentAssets ncu : nonCurrentAssets) {
                if(date.equals(ncu.getDataTime())){
                    d1 = Double.valueOf(ncu.getTnca());
                    break;
                }
            }

            double d2 = 0;
            for (NonCurrentLiabilities ncl : nonCurrentLiabilities) {
                if(date.equals(ncl.getDataTime())){
                    d2 = Double.valueOf(ncl.getTncl());
                    break;
                }
            }

            c1 = c1 + d1;
            c2 = c2 + d2;

            double crVal = 0;
            //计算基础数据
            if(0 == c1){
                data.add(c1);
            }else{
                BigDecimal b1 = new BigDecimal(c1);
                BigDecimal b2 = new BigDecimal(c2);
                crVal = b1.divide(b2 , 5 , RoundingMode.HALF_EVEN).doubleValue();
                data.add(crVal);
            }

            //计算增加率部分
            if(0 == crVal){
                sm.add(new Double(0));
            }else{
                if(0 == crsm){
                    sm.add(new Double(1));
                }else {
                    BigDecimal b1 = new BigDecimal(crVal - crsm);
                    BigDecimal b2 = new BigDecimal(crsm);
                    sm.add(b1.divide(b2, 5, RoundingMode.HALF_EVEN).doubleValue());
                }
            }
            crsm = crVal;
        }
        datas.add(data);
        datas.add(sm);
        return datas;
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


    /**
     * 获取给定日期范围，按照半年划分后的字符串集合
     * @param start
     * @param end
     * @return
     */
    private List<String> getDateByHalfYear(Date start , Date end){
        //构建两个日历对象用于处理时间
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);

        /**
         * 开始处理时间
         */
        List<String> dates = new ArrayList<>();
        int sm = DateUtils.getRecentHalfYear(start);
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
                calendar1.set(Calendar.MONTH , sm + 6);
                calendar1.set(Calendar.DATE , 0);
                dates.add(DateUtils.getDateToString(calendar1.getTime() , DateUtilEnum.SHORTBAR));
            }else {
                b = false;
            }

        }
        return dates;
    }


    /**
     * 获取给定日期范围，按照年划分后的字符串集合
     * @param start
     * @param end
     * @return
     */
    private List<String> getDateByYear(Date start , Date end){
        //构建两个日历对象用于处理时间
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);

        /**
         * 开始处理时间
         */
        List<String> dates = new ArrayList<>();
        boolean b = true;
        while (b){
            calendar1.set(Calendar.MONTH , 11);
            calendar1.set(Calendar.DATE , 31);

            long l1 = calendar1.getTimeInMillis();
            long l2 = calendar2.getTimeInMillis();

            if(l1 <= l2){
                dates.add(DateUtils.getDateToString(calendar1.getTime() , DateUtilEnum.SHORTBAR));
            }else{
                b = false;
            }

            calendar1.set(Calendar.YEAR , calendar1.get(Calendar.YEAR) + 1);
        }
        return dates;
    }


    /**
     * 根据行业id或者企业id获取企业的所有证券实体数据
     * @param stockTypeId
     * @param industryId
     * @param companyId
     * @return
     */
    private List<CompanyStock> getCompanyStock(String stockTypeId, String industryId, String companyId){
        List<CompanyStock> companyStocks = null;
        try {
            if(StringUtils.isNotEmpty(industryId)){
                companyStocks = companyStockMapper.selectCompanyStockByIndustryAndStockTypeId(industryId , stockTypeId);

            }else if(StringUtils.isNotEmpty(companyId)){
                companyStocks = new ArrayList<>();
                CompanyStock companyStock =
                        companyStockMapper.selectCompanyStockByCompanyIdAndStockTypeId(companyId , stockTypeId);
                companyStocks.add(companyStock);
            }
        }catch (Exception e){
            throw e;
        }
        return companyStocks;
    }


    /**
     * 根据给定类型获取给定时间范围的日期字符串集合（给定间隔）
     * @param sm
     * @param start
     * @param end
     * @return
     */
    private List<String> getDates(String sm, Date start, Date end){
        List<String> dates = null;
        switch (sm){
            case "year":
                dates = this.getDateByYear(start , end);
                break;
            case "halfYear":
                dates = this.getDateByHalfYear(start , end);
                break;
            case "quarter":
                dates = this.getDateByQuarter(start , end);
                break;
        }
        return dates;
    }
}



